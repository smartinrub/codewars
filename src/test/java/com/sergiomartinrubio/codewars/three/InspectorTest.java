package com.sergiomartinrubio.codewars.three;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class InspectorTest {
    @Test
    public void preliminaryTraining() {
        Inspector inspector = new Inspector();
        inspector.receiveBulletin("Entrants require passport\nAllow citizens of Arstotzka, Obristan, Antegria\nWanted by the State: Hubert Popovic\nForeigners require access permit\nCitizens of Arstotzka require ID card");

        Map<String, String> josef = new HashMap<>();
        josef.put("passport", "ID#: GC07D-FU8AR\nNATION: Arstotzka\nNAME: Costanza, Josef\nDOB: 1933.11.28\nSEX: M\nISS: East Grestin\nEXP: 1983.03.15");

        Map<String, String> guyovich = new HashMap<>();
        guyovich.put("access_permit", "NAME: Guyovich, Russian\nNATION: Obristan\nID#: TE8M1-V3N7R\nPURPOSE: TRANSIT\nDURATION: 14 DAYS\nHEIGHT: 159cm\nWEIGHT: 60kg\nEXP: 1983.07.13");

        Map<String, String> roman = new HashMap<>();
        roman.put("passport", "ID#: WK9XA-LKM0Q\nNATION: United Federation\nNAME: Dolanski, Roman\nDOB: 1933.01.01\nSEX: M\nISS: Shingleton\nEXP: 1983.05.12");
        roman.put("grant_of_asylum", "NAME: Dolanski, Roman\nNATION: United Federation\nID#: Y3MNC-TPWQ2\nDOB: 1933.01.01\nHEIGHT: 176cm\nWEIGHT: 71kg\nEXP: 1983.09.20");


        Map<String, String> kleiner = new HashMap<>();
        kleiner.put("passport", "ID#: X6XQN-WSZFK\nNATION: Antegria\nNAME: Kleiner, Emily\nDOB: 1931.05.16\nSEX: F\nISS: Glorian\nEXP: 1984.04.01");
        kleiner.put("access_permit", "NAME: Kleiner, Emily\nNATION: Antegria\nID#: X6XQN-WSZFK\nHEIGHT: 165.0cm\nWEIGHT: 69.0kg\nEXP: 1984.01.11\nPURPOSE: IMMIGRATE\nDURATION: FOREVER");

        Map<String, String> sophia = new HashMap<>();
        sophia.put("passport", "ID#: Z9WB5-TX29A\nNATION: Obristan\nNAME: Praskovic, Sophia\nDOB: 1916.05.29\nSEX: F\nISS: Skal\nEXP: 1983.08.13");


        assertEquals("Entry denied: missing required ID card.", inspector.inspect(josef));
        assertEquals("Entry denied: missing required passport.", inspector.inspect(guyovich));
        assertEquals("Detainment: ID number mismatch.", inspector.inspect(roman));
        assertEquals("Cause no trouble.", inspector.inspect(kleiner));
        assertEquals("Entry denied: missing required access permit.", inspector.inspect(sophia));
    }

    @Test
    public void otherTest() {
        Inspector inspector = new Inspector();
        inspector.receiveBulletin("Citizens of Arstotzka require ID card\n" +
                "Deny citizens of United Federation\n" +
                "Citizens of Antegria, Impor, Kolechia require polio vaccination\n" +
                "Wanted by the State: Hector Odom\nAllow citizens of Antegria, Impor, Kolechia, Obristan, Republia, United Federation\nForeigners require rubella vaccination");

        Map<String, String> roman = new HashMap<>();
        roman.put("passport", "ID#: IZPFP-N0F5S\nNATION: Kolechia\nNAME: Larsen, Roman\nDOB: 1917.01.07\nSEX: M\nISS: West Grestin\nEXP: 1983.09.29");
        roman.put("access_permit", "NAME: Larsen, Roman\nNATION: Kolechia\nID#: IZPFP-N0F5S\nHEIGHT: 161.0cm\nWEIGHT: 64.0kg\nEXP: 1983.08.28\nPURPOSE: TRANSIT\nDURATION: 2 DAYS");
        roman.put("certificate_of_vaccination", "NAME: Larsen, Roman\nID#: IZPFP-N0F5S\nVACCINES: hepatitis B, typhus, measles");

        assertEquals("Entry denied: missing required vaccination.", inspector.inspect(roman));
    }

    @Test
    public void otherTest2() {
        Inspector inspector = new Inspector();
        inspector.receiveBulletin("Entrants require polio vaccination\nEntrants no longer require polio vaccination\nCitizens of Antegria, Kolechia, Obristan, United Federation no longer require HPV vaccination\n" +
                "Citizens of Impor, Republia, Antegria require cowpox vaccination\nEntrants require yellow fever vaccination\nEntrants no longer require yellow fever vaccination\nEntrants require HPV vaccination\nEntrants no longer require HPV vaccination\nEntrants require polio vaccination\nEntrants no longer require polio vaccination");

        Map<String, String> malva = new HashMap<>();
        malva.put("passport", "ID#: IZPFP-N0F5S\nNATION: Arstotzka\nNAME: Larsen, Roman\nDOB: 1917.01.07\nSEX: M\nISS: West Grestin\nEXP: 1983.09.29");

        assertEquals("Glory to Arstotzka.", inspector.inspect(malva));
    }

}