package net.forsteri.createindustrialchemistry.datagen;

import com.simibubi.create.foundation.utility.FilesHelper;
import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@ParametersAreNonnullByDefault
public class LangMerger implements DataProvider {

    private final DataGenerator gen;

    public LangMerger(DataGenerator gen) {
        this.gen = gen;
    }

    @Override
    public void run(HashCache pCache) {
        Path path = gen.getOutputFolder()
                .resolve("assets/" + CreateIndustrialChemistry.MOD_ID + "/lang/" + "en_us.json");

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            try (InputStream resourceStream = ClassLoader.getSystemResourceAsStream("assets/" + CreateIndustrialChemistry.MOD_ID + "/lang/default/" + "en_us.json")) {
                assert resourceStream != null;
                String result = IOUtils.toString(resourceStream, StandardCharsets.UTF_8);
                writer.write(result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull String getName() {
        return "Lang merger";
    }
}
