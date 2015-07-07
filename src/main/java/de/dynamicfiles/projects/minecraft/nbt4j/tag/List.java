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
public class List implements Tag {

    private final java.util.List<Tag> entries = new ArrayList<>();
    private String name = "";

    public static List readFromStream(DataInputStream inputStream) throws IOException {
        List listTag = new List();
        listTag.setName(TagReader.getNameOfTag(inputStream, listTag.getType()));
        readPayload(inputStream, listTag);
        return listTag;
    }

    public static void readPayload(DataInputStream inputStream, de.dynamicfiles.projects.minecraft.nbt4j.tag.List listTag) throws IOException {
        TagType tagType = TagReader.getNextTagType(inputStream);
        int entryCount = inputStream.readInt();
        // payload-data does NOT have any labels (tag-names)
        for( int index = 0; index < entryCount; index += 1 ){
            switch(tagType) {
                case BYTE:
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.Byte byteTag = new de.dynamicfiles.projects.minecraft.nbt4j.tag.Byte();
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.Byte.readPayload(inputStream, byteTag);
                    listTag.getEntries().add(byteTag);
                    break;
                case SHORT:
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.Short shortTag = new de.dynamicfiles.projects.minecraft.nbt4j.tag.Short();
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.Short.readPayload(inputStream, shortTag);
                    listTag.getEntries().add(shortTag);
                    break;
                case INT:
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.Int intTag = new de.dynamicfiles.projects.minecraft.nbt4j.tag.Int();
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.Int.readPayload(inputStream, intTag);
                    listTag.getEntries().add(intTag);
                    break;
                case LONG:
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.Long longTag = new de.dynamicfiles.projects.minecraft.nbt4j.tag.Long();
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.Long.readPayload(inputStream, longTag);
                    listTag.getEntries().add(longTag);
                    break;
                case FLOAT:
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.Float floatTag = new de.dynamicfiles.projects.minecraft.nbt4j.tag.Float();
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.Float.readPayload(inputStream, floatTag);
                    listTag.getEntries().add(floatTag);
                    break;
                case DOUBLE:
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.Double doubleTag = new de.dynamicfiles.projects.minecraft.nbt4j.tag.Double();
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.Double.readPayload(inputStream, doubleTag);
                    listTag.getEntries().add(doubleTag);
                    break;
                case BYTEARRAY:
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.ByteArray byteArrayTag = new de.dynamicfiles.projects.minecraft.nbt4j.tag.ByteArray();
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.ByteArray.readPayload(inputStream, byteArrayTag);
                    listTag.getEntries().add(byteArrayTag);
                    break;
                case STRING:
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.NBTString stringTag = new de.dynamicfiles.projects.minecraft.nbt4j.tag.NBTString();
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.NBTString.readPayload(inputStream, stringTag);
                    listTag.getEntries().add(stringTag);
                    break;
                case LIST:
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.List nestedListTag = new de.dynamicfiles.projects.minecraft.nbt4j.tag.List();
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.List.readPayload(inputStream, nestedListTag);
                    listTag.getEntries().add(nestedListTag);
                    break;
                case COMPOUND:
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.Compound nestedCompoundTag = new de.dynamicfiles.projects.minecraft.nbt4j.tag.Compound();
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.Compound.readPayload(inputStream, nestedCompoundTag);
                    listTag.getEntries().add(nestedCompoundTag);
                    break;
                case INTARRAY:
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.IntArray intArrayTag = new de.dynamicfiles.projects.minecraft.nbt4j.tag.IntArray();
                    de.dynamicfiles.projects.minecraft.nbt4j.tag.IntArray.readPayload(inputStream, intArrayTag);
                    listTag.getEntries().add(intArrayTag);
                    break;
                default:
                    // may happen when having corrupted data (e.g. hitting Tag_END)
                    break;
            }
        }
    }

    @Override
    public TagType getType() {
        return TagType.LIST;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String newName) {
        name = newName;
    }

    public java.util.List<Tag> getEntries() {
        return entries;
    }

    public Tag getEntry(int index) {
        if( index >= 0 && index <= entries.size() - 1 ){
            return entries.get(index);
        }
        throw new IllegalStateException("Index out of boundaries.");
    }

}
