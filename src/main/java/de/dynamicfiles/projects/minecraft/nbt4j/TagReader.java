package de.dynamicfiles.projects.minecraft.nbt4j;

import de.dynamicfiles.projects.minecraft.nbt4j.tag.Tag;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Source:
 * http://web.archive.org/web/20110723210920/http://www.minecraft.net/docs/NBT.txt
 * http://minecraft.gamepedia.com/NBT_format
 *
 * @author FibreFoX
 */
public class TagReader {

    private TagReader() {
        // NO-OP
    }

    public static String getNameOfTag(DataInputStream inputStream, TagType tagType) throws IOException {
        if( !tagType.hasName() ){
            return "";
        }

        // first two bytes specify the length of the name
        int nameLength = inputStream.readShort();

        // name is stored as UTF-8
        byte[] nameBytes = new byte[nameLength];
        inputStream.readFully(nameBytes, 0, nameLength);

        // create java-string by using gathered utf-8 charset
        return new String(nameBytes, Charset.forName("UTF-8"));
    }

    public static TagType getNextTagType(DataInputStream inputStream) throws IOException {
        // first byte always marks the type of the tag
        int someID = inputStream.readByte();
        TagType tagTypeID = TagType.getTagTypeByID(someID);
        if( tagTypeID == null ){
            return null;
        }
        return tagTypeID;
    }

    public static Tag getNextTag(DataInputStream inputStream) throws IOException {
        TagType tagTypeID = getNextTagType(inputStream);
        if( tagTypeID == null ){
            return null;
        }
        switch(tagTypeID) {
            case END:
                return de.dynamicfiles.projects.minecraft.nbt4j.tag.End.readFromStream(inputStream);
            case BYTE:
                return de.dynamicfiles.projects.minecraft.nbt4j.tag.Byte.readFromStream(inputStream);
            case SHORT:
                return de.dynamicfiles.projects.minecraft.nbt4j.tag.Short.readFromStream(inputStream);
            case INT:
                return de.dynamicfiles.projects.minecraft.nbt4j.tag.Int.readFromStream(inputStream);
            case LONG:
                return de.dynamicfiles.projects.minecraft.nbt4j.tag.Long.readFromStream(inputStream);
            case FLOAT:
                return de.dynamicfiles.projects.minecraft.nbt4j.tag.Float.readFromStream(inputStream);
            case DOUBLE:
                return de.dynamicfiles.projects.minecraft.nbt4j.tag.Double.readFromStream(inputStream);
            case BYTEARRAY:
                return de.dynamicfiles.projects.minecraft.nbt4j.tag.ByteArray.readFromStream(inputStream);
            case LIST:
                return de.dynamicfiles.projects.minecraft.nbt4j.tag.List.readFromStream(inputStream);
            case COMPOUND:
                return de.dynamicfiles.projects.minecraft.nbt4j.tag.Compound.readFromStream(inputStream);
            case INTARRAY:
                return de.dynamicfiles.projects.minecraft.nbt4j.tag.IntArray.readFromStream(inputStream);
            default:
                return null;
        }
    }
}
