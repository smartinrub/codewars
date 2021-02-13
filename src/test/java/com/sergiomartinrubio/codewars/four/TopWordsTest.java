package com.sergiomartinrubio.codewars.four;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class TopWordsTest {

    @Test
    public void sampleTests() {
        assertEquals(Arrays.asList("e", "d", "a"), TopWords.top3("a a a  b  c c  d d d d  e e e e e"));
        assertEquals(Arrays.asList("e", "ddd", "aa"), TopWords.top3("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e"));
        assertEquals(Arrays.asList("won't", "wont"), TopWords.top3("  //wont won't won't "));
        assertEquals(Arrays.asList("e"), TopWords.top3("  , e   .. "));
        assertEquals(Arrays.asList(), TopWords.top3("  ...  "));
        assertEquals(Arrays.asList(), TopWords.top3("  '  "));
        assertEquals(Arrays.asList(), TopWords.top3("  '''  "));
        assertEquals(Arrays.asList("a", "of", "on"), TopWords.top3(Stream
                .of("In a village of La Mancha, the name of which I have no desire to call to",
                        "mind, there lived not long since one of those gentlemen that keep a lance",
                        "in the lance-rack, an old buckler, a lean hack, and a greyhound for",
                        "coursing. An olla of rather more beef than mutton, a salad on most",
                        "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra",
                        "on Sundays, made away with three-quarters of his income.")
                .collect(Collectors.joining("\n"))));
    }

    @Test
    public void otherTests() {
        assertEquals(Arrays.asList("mkc", "bei", "hczzvq"), TopWords.top3("Bei Bei mKC rLdR.SdlQTDwORU SdlQTDwORU Bei,HCZZvq!SdlQTDwORU mKC Bei mKC rLdR/mKC SdlQTDwORU-SdlQTDwORU:SdlQTDwORU mKC HCZZvq/HCZZvq Bei SdlQTDwORU Bei Bei;rLdR HCZZvq/Bei Bei rLdR SdlQTDwORU!rLdR_SdlQTDwORU.rLdR HCZZvq?HCZZvq HCZZvq mKC Bei?SdlQTDwORU mKC;mKC mKC mKC:rLdR?mKC Bei-mKC rLdR,rLdR HCZZvq HCZZvq HCZZvq HCZZvq mKC-Bei mKC rLdR/Bei_HCZZvq,SdlQTDwORU:mKC-rLdR?SdlQTDwORU Bei mKC SdlQTDwORU SdlQTDwORU,Bei?rLdR SdlQTDwORU;HCZZvq rLdR,Bei/Bei rLdR HCZZvq SdlQTDwORU mKC HCZZvq rLdR HCZZvq_mKC;SdlQTDwORU:Bei-HCZZvq mKC:Bei Bei:HCZZvq SdlQTDwORU mKC-HCZZvq rLdR?SdlQTDwORU mKC-mKC?SdlQTDwORU Bei SdlQTDwORU rLdR mKC!Bei rLdR.rLdR?rLdR rLdR mKC.HCZZvq Bei?HCZZvq/rLdR/HCZZvq/Bei HCZZvq;rLdR Bei_Bei SdlQTDwORU.HCZZvq mKC-SdlQTDwORU Bei_rLdR-mKC mKC HCZZvq rLdR,"));
    }

    @Test
    public void Tests2() {
        assertEquals(Arrays.asList("a", "b", "c"), TopWords.top3("a a c b b"));
    }

    @Test
    public void Tests3() {
        assertEquals(Arrays.asList("qalbwyp", "pskrbssfhi", "tkyktl"), TopWords.top3("PsKrbssfhI TOvReW PsKrbssfhI.AeKZ qalBwYp!qalBwYp tkyktl PsKrbssfhI.tkyktl tkyktl/PsKrbssfhI.tkyktl PsKrbssfhI;uKbtxA:TOvReW.Lxtmdagc qalBwYp-uKbtxA:qalBwYp tkyktl/Lxtmdagc_tkyktl:PsKrbssfhI Lxtmdagc qalBwYp TOvReW tkyktl qalBwYp uKbtxA uKbtxA;qalBwYp!tkyktl qalBwYp!PsKrbssfhI;tkyktl Lxtmdagc AeKZ.qalBwYp uKbtxA uKbtxA;tkyktl-AeKZ AeKZ Lxtmdagc,qalBwYp uKbtxA-PsKrbssfhI-tkyktl tkyktl.TOvReW PsKrbssfhI/qalBwYp.tkyktl qalBwYp tkyktl,tkyktl uKbtxA Lxtmdagc!qalBwYp/Lxtmdagc.PsKrbssfhI TOvReW qalBwYp PsKrbssfhI qalBwYp!qalBwYp,PsKrbssfhI tkyktl:uKbtxA?tkyktl AeKZ/PsKrbssfhI:tkyktl PsKrbssfhI qalBwYp PsKrbssfhI?PsKrbssfhI;PsKrbssfhI tkyktl uKbtxA;qalBwYp PsKrbssfhI uKbtxA-qalBwYp qalBwYp AeKZ tkyktl.TOvReW:tkyktl?tkyktl_uKbtxA_PsKrbssfhI!PsKrbssfhI:Lxtmdagc.Lxtmdagc.uKbtxA,qalBwYp/PsKrbssfhI tkyktl!PsKrbssfhI_tkyktl Lxtmdagc!tkyktl PsKrbssfhI:uKbtxA-TOvReW uKbtxA?qalBwYp.tkyktl TOvReW.qalBwYp;qalBwYp qalBwYp PsKrbssfhI.uKbtxA PsKrbssfhI.PsKrbssfhI Lxtmdagc;PsKrbssfhI,qalBwYp.tkyktl TOvReW qalBwYp TOvReW qalBwYp!uKbtxA "));
    }

    @Test
    public void Tests4() {
        assertEquals(Arrays.asList("pwnlozn", "fejy", "dtvcxspmk"), TopWords.top3("PwNLOzN dSTToG?UkZqGp dtvcXspmK tWdZT;FeJY PwNLOzN?dSTToG;PwNLOzN tWdZT PwNLOzN:PwNLOzN.dtvcXspmK,FeJY FeJY.FeJY.dSTToG PwNLOzN!dSTToG vpUa?FeJY vpUa.tWdZT npePFllMG/zIEckXQ-zIEckXQ.PwNLOzN dtvcXspmK PwNLOzN vpUa jNMf;dSTToG jNMf/tWdZT FeJY jNMf FeJY-jNMf,npePFllMG PwNLOzN!UkZqGp xXZzhTg.PwNLOzN UkZqGp dSTToG xXZzhTg FeJY UkZqGp!npePFllMG zIEckXQ npePFllMG-xXZzhTg PwNLOzN/PwNLOzN PwNLOzN-UkZqGp jNMf dtvcXspmK tWdZT!zIEckXQ jNMf;npePFllMG-dtvcXspmK PwNLOzN,tWdZT!tWdZT:zIEckXQ-zIEckXQ dSTToG_npePFllMG dtvcXspmK UkZqGp UkZqGp dtvcXspmK;dtvcXspmK UkZqGp,PwNLOzN PwNLOzN.dtvcXspmK,UkZqGp dSTToG/vpUa-npePFllMG,vpUa npePFllMG!UkZqGp UkZqGp/tWdZT/FeJY dSTToG.zIEckXQ tWdZT/dtvcXspmK;UkZqGp?npePFllMG xXZzhTg_zIEckXQ:dSTToG dSTToG?npePFllMG.tWdZT xXZzhTg dSTToG-xXZzhTg?PwNLOzN.vpUa UkZqGp PwNLOzN,FeJY dSTToG FeJY dtvcXspmK-dSTToG zIEckXQ!npePFllMG UkZqGp dtvcXspmK xXZzhTg FeJY dtvcXspmK FeJY;PwNLOzN?PwNLOzN:dtvcXspmK UkZqGp_dtvcXspmK jNMf PwNLOzN_jNMf,jNMf/dtvcXspmK;tWdZT:FeJY_vpUa dtvcXspmK.npePFllMG dSTToG_zIEckXQ;vpUa.UkZqGp PwNLOzN tWdZT_UkZqGp zIEckXQ?zIEckXQ vpUa-FeJY!dtvcXspmK vpUa-jNMf zIEckXQ!zIEckXQ.tWdZT!npePFllMG.jNMf!tWdZT:UkZqGp dtvcXspmK jNMf/dSTToG.zIEckXQ zIEckXQ tWdZT/PwNLOzN_UkZqGp!FeJY:jNMf zIEckXQ_npePFllMG jNMf zIEckXQ dtvcXspmK?jNMf PwNLOzN FeJY;PwNLOzN UkZqGp.dSTToG:tWdZT.npePFllMG vpUa dtvcXspmK/npePFllMG!npePFllMG PwNLOzN.vpUa UkZqGp_dtvcXspmK zIEckXQ zIEckXQ vpUa zIEckXQ/xXZzhTg npePFllMG_FeJY-PwNLOzN-dtvcXspmK npePFllMG:jNMf jNMf!npePFllMG FeJY zIEckXQ UkZqGp_PwNLOzN PwNLOzN FeJY?FeJY,vpUa vpUa xXZzhTg FeJY;FeJY FeJY FeJY npePFllMG/dtvcXspmK/zIEckXQ.dtvcXspmK zIEckXQ_dtvcXspmK-FeJY npePFllMG_zIEckXQ:UkZqGp-FeJY "));
    }

  @Test
    public void Tests5() {
        assertEquals(Arrays.asList("algjrrldfc", "bczwgq", "xsh'"), TopWords.top3("BCZwGq ALGJrrldfC.qZSfNe BCZwGq!BCZwGq-xSh' ALGJrrldfC;ALGJrrldfC ALGJrrldfC_BCZwGq BCZwGq ALGJrrldfC,xSh'!ALGJrrldfC/ALGJrrldfC qZSfNe!BCZwGq BCZwGq ZPFJzyrj ALGJrrldfC xSh' ZPFJzyrj xSh' ALGJrrldfC.ALGJrrldfC;ALGJrrldfC xSh' ZPFJzyrj,qZSfNe?ALGJrrldfC BCZwGq-ALGJrrldfC,xSh',xSh'_ALGJrrldfC qZSfNe qZSfNe_BCZwGq:ALGJrrldfC/ALGJrrldfC;xSh'!xSh'!ALGJrrldfC,ALGJrrldfC BCZwGq xSh' BCZwGq BCZwGq BCZwGq xSh'!BCZwGq!BCZwGq qZSfNe-ALGJrrldfC.BCZwGq,ALGJrrldfC xSh',BCZwGq BCZwGq!BCZwGq cikSisPAx ALGJrrldfC qZSfNe ALGJrrldfC?ALGJrrldfC_ALGJrrldfC.ALGJrrldfC ALGJrrldfC_BCZwGq,qZSfNe xSh' "));
    }

    @Test
    public void Tests6() {
        assertEquals(Arrays.asList("nhtxektn"), TopWords.top3("nHTxeKtn nHTxeKtn/nHTxeKtn nHTxeKtn nHTxeKtn/nHTxeKtn nHTxeKtn nHTxeKtn?nHTxeKtn nHTxeKtn nHTxeKtn_nHTxeKtn:nHTxeKtn nHTxeKtn!nHTxeKtn nHTxeKtn nHTxeKtn nHTxeKtn nHTxeKtn nHTxeKtn/nHTxeKtn nHTxeKtn,nHTxeKtn.nHTxeKtn?nHTxeKtn/"));
    }

}