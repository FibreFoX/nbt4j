package de.dynamicfiles.projects.minecraft.nbt4j.tag;

import de.dynamicfiles.projects.minecraft.nbt4j.TagType;
import de.dynamicfiles.projects.minecraft.nbt4j.TagReader;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author FibreFoX
 */
public class Int implements Tag {

    private String name = "";
    private int value = 0;

    public static Int readFromStream(DataInputStream inputStream) throws IOException {
        Int intTag = new Int();
        intTag.setName(TagReader.getNameOfTag(inputStream, intTag.getType()));
        readPayload(inputStream, intTag);
        return intTag;
    }

    public static void readPayload(DataInputStream inputStream, de.dynamicfiles.projects.minecraft.nbt4j.tag.Int intTag) throws IOException {
        intTag.setValue(inputStream.readInt());
    }

    @Override
    public TagType getType() {
        return TagType.INT;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
