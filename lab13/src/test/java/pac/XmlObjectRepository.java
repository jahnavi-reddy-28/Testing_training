package pac;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class XmlObjectRepository {

    public static String getlocator(String locatorName) {

        InputStream inputStream = null;

        try {

            /*
             * 1. First try classpath
             * src/test/resources/objectrepository.xml
             */
            inputStream = XmlObjectRepository.class
                    .getClassLoader()
                    .getResourceAsStream("objectrepository.xml");

            /*
             * 2. If not found, try project root
             */
            if (inputStream == null) {

                File file = new File("objectrepository.xml");

                if (file.exists()) {

                    inputStream = new FileInputStream(file);

                    System.out.println(
                            "XML found in project root: "
                                    + file.getAbsolutePath()
                    );
                }
            }

            /*
             * 3. Try src/test/resources
             */
            if (inputStream == null) {

                File file = new File(
                        "src/test/resources/objectrepository.xml"
                );

                if (file.exists()) {

                    inputStream = new FileInputStream(file);

                    System.out.println(
                            "XML found in src/test/resources: "
                                    + file.getAbsolutePath()
                    );
                }
            }

            /*
             * 4. Try src/main/resources
             */
            if (inputStream == null) {

                File file = new File(
                        "src/main/resources/objectrepository.xml"
                );

                if (file.exists()) {

                    inputStream = new FileInputStream(file);

                    System.out.println(
                            "XML found in src/main/resources: "
                                    + file.getAbsolutePath()
                    );
                }
            }

            /*
             * 5. If still not found, throw useful error
             */
            if (inputStream == null) {

                throw new RuntimeException(
                        "objectrepository.xml was not found.\n"
                        + "Please put it in one of these locations:\n"
                        + "1. src/test/resources/objectrepository.xml\n"
                        + "2. src/main/resources/objectrepository.xml\n"
                        + "3. Project root/objectrepository.xml"
                );
            }

            /*
             * Read XML
             */
            SAXReader reader = new SAXReader();

            Document document = reader.read(inputStream);

            /*
             * Find locator
             */
            Node node = document.selectSingleNode(
                    "//" + locatorName
            );

            if (node == null) {

                throw new RuntimeException(
                        "Locator not found in objectrepository.xml: "
                                + locatorName
                );
            }

            /*
             * dom4j Node does not have getTextTrim().
             * Use getText().trim().
             */
            String locator = node.getText().trim();

            if (locator.isEmpty()) {

                throw new RuntimeException(
                        "Locator value is empty for: "
                                + locatorName
                );
            }

            System.out.println(
                    "Locator loaded: "
                            + locatorName
                            + " = "
                            + locator
            );

            return locator;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to read locator: "
                            + locatorName,
                    e
            );

        } finally {

            if (inputStream != null) {

                try {

                    inputStream.close();

                } catch (Exception ignored) {
                }
            }
        }
    }
}