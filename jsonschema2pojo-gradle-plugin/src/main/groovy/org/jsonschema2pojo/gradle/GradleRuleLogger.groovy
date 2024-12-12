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

import org.gradle.api.logging.Logger
import org.jsonschema2pojo.AbstractRuleLogger

class GradleRuleLogger extends AbstractRuleLogger {

    Logger Logger

    GradleRuleLogger(Logger logger) {
        super()
        logger.info("Initializing {}", GradleRuleLogger.class)
        this.logger = logger
    }

    @Override
    protected void doDebug(String msg) {
        logger.debug(msg)
    }

    @Override
    protected void doError(String msg, Throwable e) {
        if(e != null) {
            logger.error(msg, Exception(msg, e))
        } else {
            logger.error(msg)
        }
    }

    @Override
    protected void doInfo(String msg) {
        logger.info(msg)
    }

    @Override
    protected void doTrace(String msg) {
        logger.trace(msg)
    }

    @Override
    protected void doWarn(String msg, Throwable e) {
        if(e != null) {
            logger.warn(msg, Exception(msg, e))
        } else {
            logger.warn(msg)
        }
    }

    @Override
    protected boolean isDebugEnabled() {
        return logger.isDebugEnabled()
    }

    @Override
    protected boolean isErrorEnabled() {
        return logger.isErrorEnabled()
    }

    @Override
    protected boolean isInfoEnabled() {
        return logger.isInfoEnabled()
    }

    @Override
    protected boolean isTraceEnabled() {
        return logger.isTraceEnabled()
    }

    @Override
    protected boolean isWarnEnabled() {
        return logger.isWarnEnabled()
    }
}