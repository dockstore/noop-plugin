/*
 *    Copyright 2016 OICR
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package io.dockstore.provision;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import ro.fortsoft.pf4j.Extension;
import ro.fortsoft.pf4j.Plugin;
import ro.fortsoft.pf4j.PluginWrapper;
import ro.fortsoft.pf4j.RuntimeMode;

/**
 * @author dyuen
 */
public class NoOpPlugin extends Plugin {

    public NoOpPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        // for testing the development mode
        if (RuntimeMode.DEVELOPMENT.equals(wrapper.getRuntimeMode())) {
            System.out.println(StringUtils.upperCase("NoOpPlugin development mode"));
        }
    }

    @Override
    public void stop() {
        System.out.println("NoOpPlugin.stop()");
    }


    @Extension
    public static class NoOpProvision implements ProvisionInterface {

        public void setConfiguration(Map<String, String> map) {
        }

        public Set<String> schemesHandled() {
            return new HashSet<>(Lists.newArrayList("s3"));
        }

        public boolean downloadFrom(String sourcePath, Path destination) {
            System.out.println("\"Downloading\" from " + sourcePath + " to " + destination);
            return true;
        }

        public boolean uploadTo(String destPath, Path sourceFile, Optional<String> metadata) {
            System.out.println("\"Uploading\" from " + sourceFile + " to " + destPath + " with " + metadata.orElse("") );
            return true;
        }

    }

}

