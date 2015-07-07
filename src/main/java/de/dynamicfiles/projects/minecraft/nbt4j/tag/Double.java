package de.dynamicfiles.projects.minecraft.nbt4j.tag;

import de.dynamicfiles.projects.minecraft.nbt4j.TagType;
import de.dynamicfiles.projects.minecraft.nbt4j.TagReader;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author FibreFoX
 */
public class Double implements Tag {

    private double value = 0.0d;
    private String name = "";

    public static Double readFromStream(DataInputStream inputStream) throws IOException {
        Double doubleTag = new Double();
        doubleTag.setName(TagReader.getNameOfTag(inputStream, doubleTag.getType()));
        readPayload(inputStream, doubleTag);
        return doubleTag;
    }

    public static void readPayload(DataInputStream inputStream, de.dynamicfiles.projects.minecraft.nbt4j.tag.Double doubleTag) throws IOException {
        doubleTag.setValue(inputStream.readDouble());
    }

    @Override
    public TagType getType() {
        return TagType.DOUBLE;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
