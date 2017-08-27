package integration.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility for loading the test properties.
 */
public class TestProperties {
    private static Properties properties = new Properties();
    private static TestProperties testProperties = new TestProperties();

    public static Properties getTestProperties() {
        return properties;
    }

    private TestProperties()  {
        InputStream stream = null;
        try {
            stream = TestProperties.class.getResourceAsStream("/dev.properties");
            properties.load(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    // left empty on purpose.
                }
            }
        }

    }
}
