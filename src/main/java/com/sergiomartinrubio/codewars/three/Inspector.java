package com.sergiomartinrubio.codewars.three;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Inspector {
    private static final LocalDate EXPIRATION_DATE = LocalDate.of(1982, 11, 22);

    Set<String> allowedCountries = new HashSet<>();
    Map<String, List<String>> vaccinationsByCountry = new HashMap<>();
    Set<String> wantedCriminals = new HashSet<>();
    Map<String, List<String>> requirementsByType = new HashMap<>();

    public void receiveBulletin(String bulletin) {
        System.out.println("BULLETIN: " + bulletin);

        String[] lines = bulletin.split("\n");
        for (String line : lines) {
            if (line.contains("vaccination")) {
                String[] vaccinations = line.substring(line.indexOf("require ") + "require ".length(), line.indexOf(" vaccination"))
                        .split(",");
                if (line.contains("no longer")) {
                    if (line.contains("Foreigners")) {
                        vaccinationsByCountry.get("Foreigners").removeAll(Arrays.asList(vaccinations));

                        for (Map.Entry<String, List<String>> entry: vaccinationsByCountry.entrySet()){

                            if (!entry.getKey().equals("Arstotzka")) {
                                entry.getValue().removeAll(Arrays.asList(vaccinations));
                            }
                        }
                    } else if (line.contains("Entrants")) {
                        vaccinationsByCountry.get("Entrants").removeAll(Arrays.asList(vaccinations));

                        for (Map.Entry<String, List<String>> entry: vaccinationsByCountry.entrySet()){
                            entry.getValue().removeAll(Arrays.asList(vaccinations));
                        }
                    } else {
                        String[] countries = line.substring(line.indexOf("Citizens of ") + "Citizens of ".length(), line.indexOf(" require"))
                                .split(", ");

                        for (String country : countries) {
                            vaccinationsByCountry.putIfAbsent(country, new ArrayList<>());
                            vaccinationsByCountry.get(country).removeAll(Arrays.asList(vaccinations));
                        }
                    }
                } else {
                    if (line.contains("Foreigners")) {
                        vaccinationsByCountry.putIfAbsent("Foreigners", new ArrayList<>());
                        vaccinationsByCountry.get("Foreigners").addAll(Arrays.asList(vaccinations));
                    } else if (line.contains("Entrants")) {
                        vaccinationsByCountry.putIfAbsent("Entrants", new ArrayList<>());
                        vaccinationsByCountry.get("Entrants").addAll(Arrays.asList(vaccinations));
                    } else {
                        String[] countries = line.substring(line.indexOf("Citizens of ") + "Citizens of ".length(), line.indexOf(" require"))
                                .split(", ");
                        for (String country : countries) {
                            vaccinationsByCountry.putIfAbsent(country, new ArrayList<>());
                            vaccinationsByCountry.get(country).addAll(Arrays.asList(vaccinations));
                        }
                    }
                }

            } else if (line.contains("require")) {
                List<String> requirements = Arrays.stream(line.substring(line.indexOf("require") + "require".length()).split(","))
                        .map(String::trim)
                        .collect(Collectors.toList());

                String country = line.substring(line.substring(0, line.indexOf("require") - 1).lastIndexOf(" ") + 1, line.indexOf("require") - 1);

                requirementsByType.putIfAbsent(country, new ArrayList<>());
                requirementsByType.get(country).addAll(requirements);
            } else if (line.contains("Allow")) {
                Set<String> newAllowedCountries = getListOfCountries(line);
                allowedCountries.addAll(newAllowedCountries);
            } else if (line.contains("Deny")) {
                Set<String> newDeniedCountries = getListOfCountries(line);
                allowedCountries.removeAll(newDeniedCountries);
            } else if (line.contains("Wanted")) {
                wantedCriminals.add(line.substring(line.indexOf("State: ") + "State: ".length()));
            }
        }
    }

    private Set<String> getListOfCountries(String line) {
        return Arrays.stream(line.substring(line.indexOf("of") + "of".length()).split(","))
                .map(String::trim)
                .collect(Collectors.toSet());
    }

    public String inspect(Map<String, String> person) {
        System.out.println(person);
        List<Document> documents = createDocuments(person);

        // Check mismatch
        String name = null;

        for (Document document : documents) {
            if (name == null) {
                name = document.getName();

                if (wantedCriminals.contains(name)) {
                    return "Detainment: Entrant is a wanted criminal.";
                }
            } else if (!name.equals(document.getName())) {
                return "Detainment: name mismatch.";
            }
        }

        List<ForeignDocument> foreignDocuments = documents.stream()
                .filter(document -> document instanceof ForeignDocument)
                .map(document -> (ForeignDocument) document)
                .collect(Collectors.toList());

        String id = null;
        String nation = null;
        for (ForeignDocument document : foreignDocuments) {
            if (id == null) {
                id = document.getId();
            } else if (!id.equals(document.getId())) {
                return "Detainment: ID number mismatch.";
            }

            if (nation == null) {
                nation = document.getNation();
            } else if (!nation.equals(document.getNation())) {
                return "Detainment: nationality mismatch.";
            }
        }

        Optional<CertificateOfVaccination> optionalCertificateOfVaccination = documents.stream().filter(document -> document instanceof CertificateOfVaccination)
                .map(document -> (CertificateOfVaccination) document).findFirst();

        if (optionalCertificateOfVaccination.isPresent()) {
            if (id != null) {
                if (!optionalCertificateOfVaccination.get().getId().equals(id)) {
                    return "Detainment: ID number mismatch.";
                }
            }
        }

        // Check documents
        boolean isPassportProvided = documents.stream().anyMatch(document -> document instanceof Passport);

        if (!isPassportProvided) {
            return "Entry denied: missing required passport.";
        }

        List<DocumentWithExpiration> documentsWithExpiration = documents.stream()
                .filter(document -> document instanceof DocumentWithExpiration)
                .map(document -> (DocumentWithExpiration) document)
                .collect(Collectors.toList());
        for (DocumentWithExpiration document : documentsWithExpiration) {
            if (document.getExp().isBefore(EXPIRATION_DATE)) {
                return "Entry denied: " + document.getDocumentName() + " expired.";
            }
        }

        if (!"Arstotzka".equals(nation)) {
            List<String> foreignersRequirements = requirementsByType.get("Foreigners");

            if (foreignersRequirements != null) {
                for (String requirement : foreignersRequirements) {
                    if ("access permit".equals(requirement)) {
                        boolean isAccessPermitNotProvided = documents.stream().noneMatch(document -> document instanceof AccessPermit);
                        boolean isGrantOfAsylumNotProvided = documents.stream().noneMatch(document -> document instanceof GrantOfAsylum);
                        boolean isDiplomaticAuthorizationNotProvided = documents.stream().noneMatch(document -> document instanceof DiplomaticAuthorization);
                        if (isAccessPermitNotProvided) {
                            if (isGrantOfAsylumNotProvided && isDiplomaticAuthorizationNotProvided) {
                                return "Entry denied: missing required access permit.";
                            } else if (!isDiplomaticAuthorizationNotProvided) {
                                List<String> access = documents.stream()
                                        .filter(document -> document instanceof DiplomaticAuthorization)
                                        .map(document -> (DiplomaticAuthorization) document)
                                        .findFirst()
                                        .orElse(null).getAccess();
                                if (!access.contains("Arstotzka")) {
                                    return "Entry denied: invalid diplomatic authorization.";
                                }
                            }
                        }
                    }
                }
            }

            if (!allowedCountries.contains(nation)) {
                return "Entry denied: citizen of banned nation.";
            }
        }

        // Check vaccines
        if (!vaccinationsByCountry.isEmpty()) {
            if ("Arstotzka".equals(nation)) {
                List<String> vaccinesForEntrants = vaccinationsByCountry.get("Entrants");
                if (vaccinesForEntrants != null && !vaccinesForEntrants.isEmpty()) {
                    CertificateOfVaccination certificateOfVaccination = documents.stream()
                            .filter(document -> document instanceof CertificateOfVaccination)
                            .map(document -> (CertificateOfVaccination) document)
                            .findFirst()
                            .orElse(null);

                    if (certificateOfVaccination == null) {
                        return "Entry denied: missing required certificate of vaccination.";
                    }

                    if (!certificateOfVaccination.getVaccines().containsAll(vaccinesForEntrants)) {
                        return "Entry denied: missing required vaccination.";
                    }
                }
            } else {
                List<String> vaccinesForCountry = Optional.ofNullable(vaccinationsByCountry.get(nation)).orElse(new ArrayList<>());
                List<String> vaccinesForEntrants = Optional.ofNullable(vaccinationsByCountry.get("Entrants")).orElse(List.of());
                List<String> vaccinesForForeigners = Optional.ofNullable(vaccinationsByCountry.get("Foreigners")).orElse(List.of());

                vaccinesForCountry.addAll(vaccinesForEntrants);
                vaccinesForCountry.addAll(vaccinesForForeigners);

                if (!vaccinesForCountry.isEmpty()) {
                    CertificateOfVaccination certificateOfVaccination = documents.stream()
                            .filter(document -> document instanceof CertificateOfVaccination)
                            .map(document -> (CertificateOfVaccination) document)
                            .findFirst()
                            .orElse(null);
                    if (certificateOfVaccination == null) {
                        return "Entry denied: missing required certificate of vaccination.";
                    }

                    if (!certificateOfVaccination.getVaccines().containsAll(vaccinesForCountry)) {
                        return "Entry denied: missing required vaccination.";
                    }
                }

            }
        }



        Optional<String> purpose = documents.stream()
                .filter(document -> document instanceof AccessPermit)
                .map(document -> (AccessPermit) document)
                .map(AccessPermit::getPurpose)
                .findFirst();

        if (purpose.isPresent()) {
            boolean isWorkPassNotProvided = documents.stream()
                    .noneMatch(document -> document instanceof WorkPass);

            if ("WORK".equals(purpose.get()) && isWorkPassNotProvided) {
                return "Entry denied: missing required work pass.";
            }
        }

        if ("Arstotzka".equals(nation)) {
            boolean isIdCardNotProvided = documents.stream().noneMatch(document -> document instanceof IdCard);

            List<String> requirements = requirementsByType.get("Arstotzka");
            if (requirements != null && requirements.contains("ID card") && isIdCardNotProvided) {
                return "Entry denied: missing required ID card.";
            }

            return "Glory to Arstotzka.";
        } else {
            return "Cause no trouble.";
        }
    }

    private List<Document> createDocuments(Map<String, String> person) {
        List<Document> documents = new ArrayList<>();

        for (Map.Entry<String, String> documentsEntries : person.entrySet()) {
            if ("passport".equals(documentsEntries.getKey())) {
                Passport passport = new Passport();
                String[] properties = documentsEntries.getValue().split("\n");
                for (String property : properties) {
                    if (property.contains("ID#")) {
                        passport.setId(getId(property));
                    } else if (property.contains("NATION")) {
                        passport.setNation(getNation(property));
                    } else if (property.contains("NAME")) {
                        passport.setName(getFullName(property));
                    } else if (property.contains("DOB")) {
                        passport.setDob(getDob(property));
                    } else if (property.contains("SEX")) {
                        passport.setSex(getSex(property));
                    } else if (property.contains("ISS")) {
                        passport.setIss(getIss(property));
                    } else if (property.contains("EXP")) {
                        passport.setExp(getExp(property));
                    }
                }
                documents.add(passport);
            } else if ("access_permit".equals(documentsEntries.getKey())) {
                AccessPermit accessPermit = new AccessPermit();
                String[] properties = documentsEntries.getValue().split("\n");
                for (String property : properties) {
                    if (property.contains("ID#")) {
                        accessPermit.setId(getId(property));
                    } else if (property.contains("NATION")) {
                        accessPermit.setNation(getNation(property));
                    } else if (property.contains("NAME")) {
                        accessPermit.setName(getFullName(property));
                    } else if (property.contains("EXP")) {
                        accessPermit.setExp(getExp(property));
                    } else if (property.contains("PURPOSE")) {
                        accessPermit.setPurpose(getPurpose(property));
                    }
                }
                documents.add(accessPermit);
            } else if ("grant_of_asylum".equals(documentsEntries.getKey())) {
                GrantOfAsylum grantOfAsylum = new GrantOfAsylum();
                String[] properties = documentsEntries.getValue().split("\n");
                for (String property : properties) {
                    if (property.contains("ID#")) {
                        grantOfAsylum.setId(getId(property));
                    } else if (property.contains("NATION")) {
                        grantOfAsylum.setNation(getNation(property));
                    } else if (property.contains("NAME")) {
                        grantOfAsylum.setName(getFullName(property));
                    } else if (property.contains("DOB")) {
                        grantOfAsylum.setDob(getDob(property));
                    } else if (property.contains("EXP")) {
                        grantOfAsylum.setExp(getExp(property));
                    }
                }
                documents.add(grantOfAsylum);
            } else if ("ID_card".equals(documentsEntries.getKey())) {
                IdCard idCard = new IdCard();
                String[] properties = documentsEntries.getValue().split("\n");
                for (String property : properties) {
                    if (property.contains("NAME")) {
                        idCard.setName(getFullName(property));
                    }
                }
                documents.add(idCard);
            } else if ("work_pass".equals(documentsEntries.getKey())) {
                WorkPass workPass = new WorkPass();
                String[] properties = documentsEntries.getValue().split("\n");
                for (String property : properties) {
                    if (property.contains("ID#")) {
                        workPass.setId(getId(property));
                    } else if (property.contains("NATION")) {
                        workPass.setNation(getNation(property));
                    } else if (property.contains("NAME")) {
                        workPass.setName(getFullName(property));
                    } else if (property.contains("EXP")) {
                        workPass.setExp(getExp(property));
                    }
                }
                documents.add(workPass);
            } else if ("diplomatic_authorization".equals(documentsEntries.getKey())) {
                DiplomaticAuthorization diplomaticAuthorization = new DiplomaticAuthorization();
                String[] properties = documentsEntries.getValue().split("\n");
                for (String property : properties) {
                    if (property.contains("ID#")) {
                        diplomaticAuthorization.setId(getId(property));
                    } else if (property.contains("NATION")) {
                        diplomaticAuthorization.setNation(getNation(property));
                    } else if (property.contains("NAME")) {
                        diplomaticAuthorization.setName(getFullName(property));
                    } else if (property.contains("ACCESS")) {
                        diplomaticAuthorization.setAccess(getAccess(property));
                    }
                }
                documents.add(diplomaticAuthorization);
            } else if ("certificate_of_vaccination".equals(documentsEntries.getKey())) {
                CertificateOfVaccination certificateOfVaccination = new CertificateOfVaccination();
                String[] properties = documentsEntries.getValue().split("\n");
                for (String property : properties) {
                    if (property.contains("ID#")) {
                        certificateOfVaccination.setId(getId(property));
                    } else if (property.contains("NAME")) {
                        certificateOfVaccination.setName(getFullName(property));
                    } else if (property.contains("VACCINES")) {
                        certificateOfVaccination.setVaccines(Arrays.asList(getVaccines(property)));
                    }
                }
                documents.add(certificateOfVaccination);
            }
        }
        return documents;
    }

    private String[] getVaccines(String property) {
        return property
                .substring(property.indexOf("VACCINES: ") + "VACCINES: ".length())
                .split(", ");
    }

    private String getId(String property) {
        return property.substring(property.indexOf("ID#: ") + "ID#: ".length());
    }

    private String getNation(String property) {
        return property.substring(property.indexOf("NATION: ") + "NATION: ".length());
    }

    private String getFullName(String property) {
        String[] fullNameArray = property
                .substring(property.indexOf("NAME: ") + "NAME: ".length())
                .split(", ");
        return fullNameArray[1] + " " + fullNameArray[0];
    }

    private LocalDate getDob(String property) {
        String[] dateParts = property.substring(property.indexOf("DOB: ") + "DOB: ".length()).split("\\.");
        return LocalDate.of(
                Integer.parseInt(dateParts[0]),
                Integer.parseInt(dateParts[1]),
                Integer.parseInt(dateParts[2])
        );
    }

    private String getSex(String property) {
        return property.substring(property.indexOf("SEX: ") + "SEX: ".length());
    }

    private String getIss(String property) {
        return property.substring(property.indexOf("ISS: ") + "ISS: ".length());
    }

    private String getPurpose(String property) {
        return property.substring(property.indexOf("PURPOSE: ") + "PURPOSE: ".length());
    }

    private LocalDate getExp(String property) {
        String[] dateParts = property.substring(property.indexOf("EXP: ") + "EXP: ".length()).split("\\.");
        return LocalDate.of(
                Integer.parseInt(dateParts[0]),
                Integer.parseInt(dateParts[1]),
                Integer.parseInt(dateParts[2])
        );
    }

    private List<String> getAccess(String property) {
        return Arrays.asList(property.substring(property.indexOf("ACCESS: ") + "ACCESS: ".length()).split(","));
    }

    private class Document {
        private String name;
        private String documentName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDocumentName() {
            return documentName;
        }
    }

    class ForeignDocument extends Document {
        private String id;
        private String nation;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }
    }

    class DocumentWithExpiration extends ForeignDocument {
        private LocalDate exp;

        public LocalDate getExp() {
            return exp;
        }

        public void setExp(LocalDate exp) {
            this.exp = exp;
        }
    }

    class IdCard extends Document {
        public String getDocumentName() {
            return "Id Card";
        }
    }

    class Passport extends DocumentWithExpiration {
        private LocalDate dob;
        private String sex;
        private String iss;

        public LocalDate getDob() {
            return dob;
        }

        public void setDob(LocalDate dob) {
            this.dob = dob;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getIss() {
            return iss;
        }

        public void setIss(String iss) {
            this.iss = iss;
        }

        public String getDocumentName() {
            return "passport";
        }
    }

    class GrantOfAsylum extends DocumentWithExpiration {
        private String id;
        private LocalDate dob;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public LocalDate getDob() {
            return dob;
        }

        public void setDob(LocalDate dob) {
            this.dob = dob;
        }

        public String getDocumentName() {
            return "grant of asylum";
        }
    }

    class AccessPermit extends DocumentWithExpiration {
        private String purpose;

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public String getDocumentName() {
            return "access permit";
        }
    }

    class WorkPass extends DocumentWithExpiration {

        public String getDocumentName() {
            return "work pass";
        }
    }

    class DiplomaticAuthorization extends ForeignDocument {
        private List<String> access;

        public List<String> getAccess() {
            return access;
        }

        public void setAccess(List<String> access) {
            this.access = access;
        }

        public String getDocumentName() {
            return "diplomatic authorization";
        }
    }

    class CertificateOfVaccination extends Document {
        private String id;
        private List<String> vaccines;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getVaccines() {
            return vaccines;
        }

        public void setVaccines(List<String> vaccines) {
            this.vaccines = vaccines;
        }

        public String getDocumentName() {
            return "certificate of vaccination";
        }
    }
}
