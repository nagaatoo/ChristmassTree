package ru.nagatoo.christmassTree;

import java.io.File;
import java.io.Serializable;

public class MetaDataClass implements Serializable {
    public int mouseX = 0;
    public int mouseY = 0;
    public int windowX = 0;
    public int windowY = 0;
    public boolean firstRun = true;
    public File file = null;
}
