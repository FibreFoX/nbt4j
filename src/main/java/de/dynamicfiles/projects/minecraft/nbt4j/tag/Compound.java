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
public class Compound implements Tag {

    private final java.util.List<Tag> entries = new ArrayList<>();
    private String name = "";

    public static Compound readFromStream(DataInputStream inputStream) throws IOException {
        Compound compound = new Compound();
        compound.setName(TagReader.getNameOfTag(inputStream, compound.getType()));
        readPayload(inputStream, compound);
        return compound;
    }
    public static void readPayload(DataInputStream inputStream, de.dynamicfiles.projects.minecraft.nbt4j.tag.Compound compoundTag) throws IOException {
        TagType tagType = TagReader.getNextTagType(inputStream);
        while(tagType != null && tagType != TagType.END) {
            switch(tagType) {
                case BYTE:
                    compoundTag.getEntries().add(de.dynamicfiles.projects.minecraft.nbt4j.tag.Byte.readFromStream(inputStream));
                    break;
                case SHORT:
                    compoundTag.getEntries().add(de.dynamicfiles.projects.minecraft.nbt4j.tag.Short.readFromStream(inputStream));
                    break;
                case INT:
                    compoundTag.getEntries().add(de.dynamicfiles.projects.minecraft.nbt4j.tag.Int.readFromStream(inputStream));
                    break;
                case LONG:
                    compoundTag.getEntries().add(de.dynamicfiles.projects.minecraft.nbt4j.tag.Long.readFromStream(inputStream));
                    break;
                case FLOAT:
                    compoundTag.getEntries().add(de.dynamicfiles.projects.minecraft.nbt4j.tag.Float.readFromStream(inputStream));
                    break;
                case DOUBLE:
                    compoundTag.getEntries().add(de.dynamicfiles.projects.minecraft.nbt4j.tag.Double.readFromStream(inputStream));
                    break;
                case BYTEARRAY:
                    compoundTag.getEntries().add(de.dynamicfiles.projects.minecraft.nbt4j.tag.ByteArray.readFromStream(inputStream));
                    break;
                case STRING:
                    compoundTag.getEntries().add(de.dynamicfiles.projects.minecraft.nbt4j.tag.NBTString.readFromStream(inputStream));
                    break;
                case LIST:
                    compoundTag.getEntries().add(de.dynamicfiles.projects.minecraft.nbt4j.tag.List.readFromStream(inputStream));
                    break;
                case COMPOUND:
                    compoundTag.getEntries().add(de.dynamicfiles.projects.minecraft.nbt4j.tag.Compound.readFromStream(inputStream));
                    break;
                case INTARRAY:
                    compoundTag.getEntries().add(de.dynamicfiles.projects.minecraft.nbt4j.tag.IntArray.readFromStream(inputStream));
                    break;
                default:
                    // may happen when having corrupted data
                    break;
            }
            tagType = TagReader.getNextTagType(inputStream);
        }
    }

    @Override
    public TagType getType() {
        return TagType.COMPOUND;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }

    public Tag getNamedEntry(String name) {
        Tag foundTag = null;
        for( Tag tag : entries ){
            if( name.equals(tag.getName()) ){
                foundTag = tag;
            }
        }
        return foundTag;
    }

    public List<Tag> getEntries() {
        return entries;
    }

}
