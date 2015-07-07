package de.dynamicfiles.projects.minecraft.nbt4j.tag;

import de.dynamicfiles.projects.minecraft.nbt4j.TagType;
import de.dynamicfiles.projects.minecraft.nbt4j.TagReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author FibreFoX
 */
public class IntArray implements Tag {

    private String name = "";
    private final java.util.List<de.dynamicfiles.projects.minecraft.nbt4j.tag.Int> ints = new ArrayList<>();

    public static IntArray readFromStream(DataInputStream inputStream) throws IOException {
        IntArray intArrayTag = new IntArray();
        intArrayTag.setName(TagReader.getNameOfTag(inputStream, intArrayTag.getType()));
        readPayload(inputStream, intArrayTag);
        return intArrayTag;
    }

    public static void readPayload(DataInputStream inputStream, IntArray intArrayTag) throws IOException {
        int intEntryCount = inputStream.readInt();
        java.util.List<de.dynamicfiles.projects.minecraft.nbt4j.tag.Int> ints = intArrayTag.getInts();
        for( int index = 0; index < intEntryCount; index += 1 ){
            de.dynamicfiles.projects.minecraft.nbt4j.tag.Int singleIntEntry = new de.dynamicfiles.projects.minecraft.nbt4j.tag.Int();
            de.dynamicfiles.projects.minecraft.nbt4j.tag.Int.readPayload(inputStream, singleIntEntry);
            ints.add(singleIntEntry);
        }
    }

    @Override
    public TagType getType() {
        return TagType.INTARRAY;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }

    public java.util.List<de.dynamicfiles.projects.minecraft.nbt4j.tag.Int> getInts() {
        return ints;
    }

    public de.dynamicfiles.projects.minecraft.nbt4j.tag.Int getEntry(int index) {
        if( index >= 0 && index <= ints.size() - 1 ){
            return ints.get(index);
        }
        throw new IllegalStateException("Index out of boundaries.");
    }
}
