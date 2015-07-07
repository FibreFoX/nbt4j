package de.dynamicfiles.projects.minecraft.nbt4j.tag;

import de.dynamicfiles.projects.minecraft.nbt4j.TagType;
import de.dynamicfiles.projects.minecraft.nbt4j.TagReader;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author FibreFoX
 */
public class Byte implements Tag {

    private String name = "";
    private byte value = 0;

    public static Byte readFromStream(DataInputStream inputStream) throws IOException {
        Byte byteTag = new Byte();
        byteTag.setName(TagReader.getNameOfTag(inputStream, byteTag.getType()));
        readPayload(inputStream, byteTag);
        return byteTag;
    }

    public static void readPayload(DataInputStream inputStream, Byte byteTag) throws IOException {
        byteTag.setValue(inputStream.readByte());
    }

    @Override
    public TagType getType() {
        return TagType.BYTE;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

}
