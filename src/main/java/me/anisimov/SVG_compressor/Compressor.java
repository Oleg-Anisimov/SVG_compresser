package me.anisimov.SVG_compressor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

class Compressor {
    public static int compress(String... args) throws IOException {

        if (args.length < 1 || isNullOrWhitespace(args[0])) {
            System.out.println("Run app with argument: path to svg");
            return 1;
        }


        for (String pathFile : args) {

            File file = new File(pathFile);
            if (!file.exists()) {
                System.out.println("No such file: \"{args[0]}\"");
                return 2;
            }
            String newFilePath = FilenameUtils.getFullPath(pathFile)+"\\"+FilenameUtils.getBaseName(pathFile) + "_compressed.SVG";

            String match = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            String newFileText = match.replaceAll("(\\d+\\.\\d)(?:\\d+)", "$1");


            /// Круто было бы иметь возможность включать это опционально
            if (!newFileText.contains("<?xml version=\"1.0\" encoding=\"utf-8\"?>")) {
                newFileText = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + newFileText;
            }

            Files.write(Path.of(newFilePath), newFileText.getBytes(StandardCharsets.UTF_8));

        }
        System.out.println("Done!");
        return 0;
    }

    public static boolean isNullOrWhitespace(String s) {
        if (s == null)
            return true;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }


}