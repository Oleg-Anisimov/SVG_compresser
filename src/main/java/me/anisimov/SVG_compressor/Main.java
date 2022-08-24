package me.anisimov.SVG_compressor;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        // Локализация компонентов окна JFileChooser
        UIManager.put(
                "SVG_compressor.FileChooser.saveButtonText", "Сохранить");
        UIManager.put(
                "SVG_compressor.FileChooser.cancelButtonText", "Отмена");
        UIManager.put(
                "SVG_compressor.FileChooser.fileNameLabelText", "Наименование файла");
        UIManager.put(
                "SVG_compressor.FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put(
                "SVG_compressor.FileChooser.lookInLabelText", "Директория");
        UIManager.put(
                "SVG_compressor.FileChooser.saveInLabelText", "Сохранить в директории");
        UIManager.put(
                "SVG_compressor.FileChooser.folderNameLabelText", "Путь директории");

        new FileChooser();
    }
}

