package de.dynamicfiles.projects.minecraft.nbt4j.tag;

import de.dynamicfiles.projects.minecraft.nbt4j.TagType;
import de.dynamicfiles.projects.minecraft.nbt4j.TagReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FibreFoX
 */
public class ByteArray implements Tag {

    private String name = "";
    private final java.util.List<de.dynamicfiles.projects.minecraft.nbt4j.tag.Byte> bytes = new ArrayList<>();

    public static ByteArray readFromStream(DataInputStream inputStream) throws IOException {
        ByteArray byteArrayTag = new ByteArray();
        byteArrayTag.setName(TagReader.getNameOfTag(inputStream, byteArrayTag.getType()));
        readPayload(inputStream, byteArrayTag);
        return byteArrayTag;
    }
    public static void readPayload(DataInputStream inputStream, ByteArray byteArrayTag) throws IOException {
        int byteEntryCount = inputStream.readInt();
        List<Byte> bytes = byteArrayTag.getBytes();
        for( int index = 0; index < byteEntryCount; index += 1 ){
            de.dynamicfiles.projects.minecraft.nbt4j.tag.Byte singleByteEntry = new de.dynamicfiles.projects.minecraft.nbt4j.tag.Byte();
            de.dynamicfiles.projects.minecraft.nbt4j.tag.Byte.readPayload(inputStream, singleByteEntry);
            bytes.add(singleByteEntry);
        }
    }

    @Override
    public TagType getType() {
        return TagType.BYTEARRAY;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }

    public List<de.dynamicfiles.projects.minecraft.nbt4j.tag.Byte> getBytes() {
        return bytes;
    }

    public de.dynamicfiles.projects.minecraft.nbt4j.tag.Byte getEntry(int index) {
        if( index >= 0 && index <= bytes.size() - 1 ){
            return bytes.get(index);
        }
        throw new IllegalStateException("Index out of boundaries.");
    }

}
