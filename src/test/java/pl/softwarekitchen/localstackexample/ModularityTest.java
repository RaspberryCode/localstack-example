package pl.softwarekitchen.localstackexample;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModularityTest {
    @Test
    void verifyModularStructure() {
        ApplicationModules.of(LocalstackExampleApplication.class)
                .verify();
    }
}
