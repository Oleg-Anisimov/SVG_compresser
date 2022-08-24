package me.anisimov.SVG_compressor;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileChooser extends JFrame {
    private JButton btnOpenDir = null;
    private JFileChooser fileChooser = null;

    public FileChooser() {
        super("SVG_compressor.FileChooser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnOpenDir = new JButton("Открыть директорию");

        fileChooser = new JFileChooser();


        JPanel contents = new JPanel();
        JTextArea myPanel = new JTextArea(10,50);

        contents.add(btnOpenDir);

        contents.add(myPanel);
        setContentPane(contents);

        setSize(500, 300);
        setVisible(true);

        btnOpenDir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выбор директории");
                // Определение режима - только каталог
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int result = fileChooser.showOpenDialog(FileChooser.this);
                var path = fileChooser.getSelectedFile();
                File file = new File(path.toURI());
                // Если директория выбрана, покажем ее в сообщении
                if (result == JFileChooser.APPROVE_OPTION){
                    try {
                        Compressor.compress(file.getAbsolutePath());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(FileChooser.this,
                                fileChooser.getSelectedFile());
                        throw new RuntimeException(ex);

                    }
                }

            }
        });

        myPanel.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>)
                            evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        try {
                            Compressor.compress(file.getAbsolutePath());
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(FileChooser.this,
                                    fileChooser.getSelectedFile());
                            throw new RuntimeException(ex);

                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }


}



