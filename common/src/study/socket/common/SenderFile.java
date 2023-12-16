package study.socket.common;

import java.io.File;

public class SenderFile {
    private File  file;
    private String name;
    private int desc;
    private int size;
    byte[] array;

    public SenderFile(File file, int desc, int size, String name,byte [] array) {

        if (file.getName().length() < desc && file.getName().endsWith(".txt") && file.getUsableSpace() < size) {
            this.name = name;
            this.file = file;
            this.array=array;
        } else {
            System.out.println("Файл не соответствует парраметрам");
        }
    }

    public byte[] getArray() {
        return array;
    }

    public void setArray(byte[] array) {
        this.array = array;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getDesc() {
        return desc;
    }

    public void setDesc(int desc) {
        this.desc = desc;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
