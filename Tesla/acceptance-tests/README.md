commande de Compil:
    Configurator:
        Compilation : javac -d ../bin/ Configurator*.java
        Exec : java org.junit.runner.JUnitCore test.acceptance.ConfiguratorTest

        Raccourcis : cd ../src && javac -d ../bin/ Configurator*.java
                     cd ../bin && java org.junit.runner.JUnitCore test.acceptance.ConfiguratorTest
