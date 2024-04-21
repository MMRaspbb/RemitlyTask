import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonVerifierTest {
    private final JsonVerifier jsonVerifier = new JsonVerifier();
    @Test
    void simpleCorrect(){
        assertTrue(jsonVerifier.isLegit("src/test/resources/simpleCorrect.json"));
    }
    @Test
    void complextCorrect(){
        assertTrue(jsonVerifier.isLegit("src/test/resources/complexCorrect.json"));
    }
    @Test
    void notStringPolicyName(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/wrongName.json"));
    }
    @Test
    void noPolicyName(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/noName.json"));
    }
    @Test
    void notObjectPolicyDocument() {
        assertFalse(jsonVerifier.isLegit("src/test/resources/wrongDocument.json"));
    }
    @Test
    void noPolicyDocument(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/noDocument.json"));
    }
    @Test
    void versionNotString(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/notStringVersion.json"));
    }
    @Test
    void noVersion(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/noVersion.json"));
    }
    @Test
    void invalidDayVersion(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/invalidDayVersion.json"));
    }
    @Test
    void invalidMonthVersion(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/invalidMonthVersion.json"));
    }
    @Test
    void simpleNoSid(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/simpleNoSid.json"));
    }
    @Test
    void simpleSidNotString(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/simpleSidNotString.json"));
    }
    @Test
    void complexNoSid(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/complexNoSid.json"));
    }
    @Test
    void complexSidNotString(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/complexSidNotString.json"));
    }
    @Test
    void sidNotUnique(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/sidNotUnique.json"));
    }
    @Test
    void noEffect(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/noEffect.json"));
    }
    @Test
    void effectNotAllowOrDeny(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/effectNotAllowOrDeny.json"));
    }
    @Test
    void noAction(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/noAction.json"));
    }
    @Test
    void actionNotAStringNorArrayOfStrings(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/wrongAction.json"));
    }
    @Test
    void contectsOfActionArrayNotString(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/wrongActionArray.json"));
    }
    @Test
    void noResource(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/noResource.json"));
    }
    @Test
    void resourceNotString(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/resourceNotString.json"));
    }
    @Test
    void notStringOrArrayOfStringsPrincipal(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/wrongPrincipal.json"));
    }
    @Test
    void contectsOfPrincipalArrayNotString(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/wrongPrincipalArray.json"));
    }
    @Test
    void conditionNotString(){
        assertFalse(jsonVerifier.isLegit("src/test/resources/conditionNotString.json"));
    }
}