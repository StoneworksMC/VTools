package com.crazyhjonk.vtools.config;

import com.crazyhjonk.core.config.ConfigRegistrar;
import com.crazyhjonk.core.config.ConfigType;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@Getter
public enum VToolsConfigRegistrar implements ConfigRegistrar {
    BROADCAST_PREFIX(String.class, "broadcast-prefix"),;

    @Getter
    public enum Section implements ConfigRegistrar.Section {

        ;

        private final String path;
        private final String name;
        private final @Nullable File file;

        /**
         * @implNote Use null for the default config file.
         */
        Section(String name, @Nullable File file, Section... parent) {
            this.name = name;
            this.file = file;

            if (parent.length != 0) {
                this.path = parent[0].getPath() + "." + name;
            } else {
                this.path = name;
            }
        }
    }

    @Getter
    public enum File implements ConfigRegistrar.File {
        CONFIG("config"),
        ;

        private final String name;

        File(String name) {
            this.name = name;
        }
    }

    private final ConfigType<?> type;

    private final String path;
    private final String name;
    private final @Nullable Section section;

    VToolsConfigRegistrar(Class<?> type, String name, Section... parent) {
        this.type = ConfigType.of(type);
        this.name = name;

        if (parent.length != 0) {
            this.path = parent[0].getPath() + "." + name;
            this.section = parent[0];
            return;
        }

        this.section = null;
        this.path = name;
    }
    public File getFile() {
        return section != null ? section.getFile() : null;
    }
}
