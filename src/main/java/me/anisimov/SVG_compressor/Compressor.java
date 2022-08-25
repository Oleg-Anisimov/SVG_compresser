package me.anisimov.SVG_compressor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

class Compressor {
    public static String compress(String... args) throws IOException {

        String str="";

        if (args.length < 1 || isNullOrWhitespace(args[0])) {
            System.out.println("Run app with argument: path to svg");

        }


        for (String pathFile : args) {

            File file = new File(pathFile);
            if (!file.exists()) {
                System.out.println("No such file: \"{args[0]}\"");

            }
            String newFilePath = FilenameUtils.getFullPath(pathFile) + "\\" + FilenameUtils.getBaseName(pathFile) + "_compressed.SVG";

            String match = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            String newFileText = match.replaceAll("(\\d+\\.\\d)(?:\\d+)", "$1");


            /// Круто было бы иметь возможность включать это опционально
            if (!newFileText.contains("<?xml version=\"1.0\" encoding=\"utf-8\"?>")) {
                newFileText = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + newFileText;
            }

            Files.write(Path.of(newFilePath), newFileText.getBytes(StandardCharsets.UTF_8));
            float i = (match.getBytes(StandardCharsets.UTF_8).length - newFileText.getBytes(StandardCharsets.UTF_8).length)/1000;
            str="Saved " + i + " Kb";
        }

        System.out.println("Done!");
        return str;
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