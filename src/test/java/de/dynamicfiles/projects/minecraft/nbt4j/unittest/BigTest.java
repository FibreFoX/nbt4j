package de.dynamicfiles.projects.minecraft.nbt4j.unittest;

import de.dynamicfiles.projects.minecraft.nbt4j.TagReader;
import de.dynamicfiles.projects.minecraft.nbt4j.TagType;
import de.dynamicfiles.projects.minecraft.nbt4j.tag.Compound;
import de.dynamicfiles.projects.minecraft.nbt4j.tag.Tag;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.*;

/**
 * Source of bigtest.nbt: http://sourceforge.net/p/libnbt/code/ci/master/tree/bigtest.nbt?format=raw
 *
 * Due to the fact that bigtest.nbt isn't hosted at minecraft anymore, i had to check the web for it.
 * Thanks to libnbt for having this inside its sources-belly ;)
 *
 * The bigtext.nbt-file is a gzip-compressed binary file, also found inside the test-resources of this project.
 *
 * @author FibreFoX
 */
public class BigTest {

    private DataInputStream dataInputStream = null;

    @BeforeMethod
    public void openBigTestNBT() throws IOException {
        String testfile = "bigtest.nbt";
        dataInputStream = new DataInputStream(new GZIPInputStream(new FileInputStream("src/test/resources/" + testfile)));
    }

    @AfterMethod
    public void closeBigTestNBT() throws IOException {
        dataInputStream.close();
    }

    private Compound getCompoundEntry() throws IOException {
        Tag firstTag = TagReader.getNextTag(dataInputStream);
        assertNotNull(firstTag);

        // if this fails, there is a generic failure
        assertEquals(firstTag.getType(), TagType.COMPOUND, "bigtest is a big compound-file (whic nearly all nbt-files are used/created by minecraft-server)");

        return (Compound) firstTag;
    }

    @Test
    public void checkLoadingParentCompound() throws IOException {
        Compound compound = getCompoundEntry();
        assertNotNull(compound, "if this is null, there might be a problem within TagReader");
        assertEquals(compound.getEntries().size(), 11);
    }

    @Test
    public void testByteTag() throws IOException {
        Compound compound = getCompoundEntry();

        de.dynamicfiles.projects.minecraft.nbt4j.tag.Byte byteTest = (de.dynamicfiles.projects.minecraft.nbt4j.tag.Byte) compound.getNamedEntry("byteTest");
        assertNotNull(byteTest, "if this fails, there tag.Byte.readFromStream() may be broken");
        assertEquals(byteTest.getValue(), 127, "if this fails, there tag.Byte may be broken");
    }

    @Test
    public void testShortTag() throws IOException {
        Compound compound = getCompoundEntry();

        de.dynamicfiles.projects.minecraft.nbt4j.tag.Short shortTest = (de.dynamicfiles.projects.minecraft.nbt4j.tag.Short) compound.getNamedEntry("shortTest");
        assertNotNull(shortTest, "if this fails, there tag.Short.readFromStream() may be broken");
        assertEquals(shortTest.getValue(), 32767, "if this fails, there tag.Short may be broken");
    }

    @Test
    public void testIntTag() throws IOException {
        Compound compound = getCompoundEntry();

        de.dynamicfiles.projects.minecraft.nbt4j.tag.Int intTest = (de.dynamicfiles.projects.minecraft.nbt4j.tag.Int) compound.getNamedEntry("intTest");
        assertNotNull(intTest, "if this fails, there tag.Int.readFromStream() may be broken");
        assertEquals(intTest.getValue(), 2147483647, "if this fails, there tag.Int may be broken");
    }

    @Test
    public void testLongTag() throws IOException {
        Compound compound = getCompoundEntry();

        de.dynamicfiles.projects.minecraft.nbt4j.tag.Long longTest = (de.dynamicfiles.projects.minecraft.nbt4j.tag.Long) compound.getNamedEntry("longTest");
        assertNotNull(longTest, "if this fails, there tag.Long.readFromStream() may be broken");
        assertEquals(longTest.getValue(), 9223372036854775807L, "if this fails, there tag.Long may be broken");
    }

    @Test
    public void testFloatTag() throws IOException {
        Compound compound = getCompoundEntry();

        de.dynamicfiles.projects.minecraft.nbt4j.tag.Float floatTest = (de.dynamicfiles.projects.minecraft.nbt4j.tag.Float) compound.getNamedEntry("floatTest");
        assertNotNull(floatTest, "if this fails, there tag.Float.readFromStream() may be broken");
        assertEquals(floatTest.getValue(), 0.49823147f, "if this fails, there tag.Float may be broken");
    }

    @Test
    public void testDoubleTag() throws IOException {
        Compound compound = getCompoundEntry();

        de.dynamicfiles.projects.minecraft.nbt4j.tag.Double doubleTest = (de.dynamicfiles.projects.minecraft.nbt4j.tag.Double) compound.getNamedEntry("doubleTest");
        assertNotNull(doubleTest, "if this fails, there tag.Double.readFromStream() may be broken");
        assertEquals(doubleTest.getValue(), 0.4931287132182315d, "if this fails, there tag.Double may be broken");
    }

    @Test
    public void testStringTag() throws IOException {
        Compound compound = getCompoundEntry();

        de.dynamicfiles.projects.minecraft.nbt4j.tag.NBTString stringTest = (de.dynamicfiles.projects.minecraft.nbt4j.tag.NBTString) compound.getNamedEntry("stringTest");
        assertNotNull(stringTest, "if this fails, there tag.NBTString.readFromStream() may be broken");
        assertEquals(stringTest.getValue(), "HELLO WORLD THIS IS A TEST STRING ÅÄÖ!", "if this fails, there tag.NBTString may be broken");
    }

    @Test
    public void testListTagWithCompounds() throws IOException {
        Compound compound = getCompoundEntry();

        de.dynamicfiles.projects.minecraft.nbt4j.tag.List listTest = (de.dynamicfiles.projects.minecraft.nbt4j.tag.List) compound.getNamedEntry("listTest (compound)");
        assertNotNull(listTest, "if this fails, there tag.List.readFromStream() may be broken");
        List<Tag> entries = listTest.getEntries();
        assertFalse(entries.isEmpty());
        assertEquals(entries.size(), 2, "if this fails, there tag.List or tag.Compound may be broken");

        for( Tag entry : entries ){
            assertEquals(entry.getType(), TagType.COMPOUND);
        }
    }

    @Test
    public void testListTagWithLongs() throws IOException {
        Compound compound = getCompoundEntry();

        de.dynamicfiles.projects.minecraft.nbt4j.tag.List listTest = (de.dynamicfiles.projects.minecraft.nbt4j.tag.List) compound.getNamedEntry("listTest (long)");
        assertNotNull(listTest, "if this fails, there tag.List.readFromStream() may be broken");
        List<Tag> entries = listTest.getEntries();
        assertFalse(entries.isEmpty());
        assertEquals(entries.size(), 5, "if this fails, there tag.List or tag.Long may be broken");

        for( Tag entry : entries ){
            assertEquals(entry.getType(), TagType.LONG);
        }
    }

    @Test
    public void testNestedCompounds() throws IOException {
        Compound compound = getCompoundEntry();
        de.dynamicfiles.projects.minecraft.nbt4j.tag.Compound nestedCompound = (de.dynamicfiles.projects.minecraft.nbt4j.tag.Compound) compound.getNamedEntry("nested compound test");
        assertFalse(nestedCompound.getEntries().isEmpty());

        Tag egg = nestedCompound.getNamedEntry("egg");
        assertNotNull(egg);
        assertEquals(egg.getType(), TagType.COMPOUND);

        Tag ham = nestedCompound.getNamedEntry("ham");
        assertNotNull(ham);
        assertEquals(ham.getType(), TagType.COMPOUND);
    }
}
