import dataacces.PropertyReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PropertyReaderTest {

    @Test
    void readTest(){
        Assertions.assertNotNull(PropertyReader.getDataBaseProperties());
    }
}
