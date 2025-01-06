/**
 * Copyright © 2010-2017 Nokia
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */
package org.jsonschema2pojo.gradle

import org.jsonschema2pojo.rules.RuleLogger

class GradleRuleLogger implements RuleLogger {
@SuppressWarnings({"UnusedMethodParameter"})
    GradleRuleLogger(Logger logger) {
        super()
        logger.info("Initializing {}", GradleRuleLogger.class)
        this.logger = logger

    @Override
    void doDebug(String msg) {
        logger.debug(msg)
    }

    @Override
         logger.error(msg, e)
         logger.error(msg, e)
    }
    @Override
        logger.info(msg)
    }

    @Override
    void doTrace(String msg) {
        logger.trace(msg)
    }
    void doWarn(String msg, Throwable e) {
         logger.warn(msg, e)
    }

    @Override
    boolean isDebugEnabled() {
        return logger.isDebugEnabled()
    }

    @Override
    boolean isErrorEnabled() {
        return logger.isErrorEnabled()
    }

    @Override
    boolean isInfoEnabled() {
        return logger.isInfoEnabled()
    }

    @Override
    boolean isTraceEnabled() {
        return logger.isTraceEnabled()
    }

    @Override
    boolean isDebugEnabled() {
        return logger.isDebugEnabled()
    }
}