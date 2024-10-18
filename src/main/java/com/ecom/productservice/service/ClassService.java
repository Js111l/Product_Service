package com.ecom.productservice.service;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("rawtypes")
public class ClassService {
    private static final String SRC_MAIN_JAVA_PATH = "/src/main/java/";

    public Enum[] getEnumValuesByType(String enumSimpleClassName) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var classes = this.getAllSubTypesOf(Enum.class);
        var clazz = classes.stream()
                .filter(x -> x.getSimpleName().toUpperCase().equals(enumSimpleClassName.toUpperCase()))
                .findFirst()
                .orElseThrow();
        var method = clazz.getMethod("values");
        return ((Enum[]) method.invoke(null));
    }

    public List<Class> getAllSubTypesOf(Class<?> clazz) throws IOException {
        var classes = new ArrayList<String>();
        final var packageName = System.getProperty("user.dir") + SRC_MAIN_JAVA_PATH;
        this.collectClassNames(packageName, classes);

        return classes.stream()
                .flatMap(x -> {
                    var properName = x.substring(x.indexOf("java") + 5).replaceAll("/", ".").replaceAll(".java", "");
                    if (clazz.isAssignableFrom(getCLass(properName))) {
                        return Stream.of(getCLass(properName)); //filtrowanie i map w 1 operacji
                    } else {
                        return Stream.empty();
                    }
                }).toList();
    }

    private Class getCLass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void collectClassNames(String packageName, List<String> classNames) throws IOException {
        try (var xx = Files.walk(Paths.get(packageName))) {
            xx.map(Path::toFile).forEach(file -> {
                if (file.exists() && file.isDirectory()) {
                    collectClassNamesRecursively(file, classNames);
                }
            });
        }
    }

    private void collectClassNamesRecursively(File file, List<String> classes) {
        if (file.isDirectory()) {
            final var directoryFiles = file.listFiles();
            assert directoryFiles != null;
            Arrays.stream(directoryFiles)
                    .forEach(fileFromDirectory -> {
                        if (fileFromDirectory.isFile()) {
                            collectClassNamesRecursively(fileFromDirectory, classes);
                        }
                    });
        } else {
            classes.add(file.getAbsolutePath());
        }
    }

}






