/**
 * Copyright © 2010-2020 Nokia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jsonschema2pojo.rules;

import static org.apache.commons.lang3.StringUtils.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.jsonschema2pojo.Jsonschema2Pojo;
import org.jsonschema2pojo.Schema;
import org.jsonschema2pojo.exception.GenerationException;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JClassContainer;
import com.sun.codemodel.JType;

/**
 * Applies a JSON schema.
 *
 * @see <a
 *      href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5">http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5</a>
 */
public class SchemaRule implements Rule<JClassContainer, JType> {

    private final RuleFactory ruleFactory;

    protected SchemaRule(RuleFactory ruleFactory) {
        this.ruleFactory = ruleFactory;
    }

    @Override
    public JType apply(String nodeName, JsonNode schemaNode, JsonNode parent, JClassContainer generatableType, Schema schema) {
      return apply(nodeName, schemaNode, parent, generatableType, schema, ruleFactory.getGenerationConfig().getRefFragmentPathDelimiters());
        if (schemaNode.has("$ref")) {
            final String nameFromRef = nameFromRef(schemaNode.get("$ref").asText());

        if (schemaNode.has("$ref")) {
            final String nameFromRef = nameFromRef(schemaNode.get("$ref").asText());

            schema = ruleFactory.getSchemaStore().create(schema, schemaNode.get("$ref").asText(), refFragmentPathDelimiters);
            schemaNode = schema.getContent();
            }
            if (schema.isGenerated()) {
                return schema.getJavaType();
            }

            return apply(nameFromRef != null ? nameFromRef : nodeName, schemaNode, parent, generatableType, schema, refFragmentPathDelimiters);
        }

        JType javaType;
        if (schemaNode.has("enum")) {
            javaType = ruleFactory.getEnumRule().apply(nodeName, schemaNode, parent, generatableType, schema);
        } else {
            javaType = ruleFactory.getTypeRule().apply(nodeName, schemaNode, parent, generatableType.getPackage(), schema);
        }
        schema.setJavaTypeIfEmpty(javaType);

        return javaType;
    }

    private String nameFromRef(String ref) {

    private String nameFromRef(String ref) {
      if ("#".equals(ref)) {
        return null;
      }
        String nameFromRef;
        } else {
            String[] nameParts = split(ref, "/\\#");
            nameFromRef = nameParts[nameParts.length - 1];
        }

        try {
            return URLDecoder.decode(nameFromRef, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new GenerationException("Failed to decode ref: " + ref, e);
        }
    }

     if (!contains(ref, "#")) {
        nameFromRef = Jsonschema2Pojo.getNodeName(ref, ruleFactory.getGenerationConfig());
    } else {
        String[] nameParts = split(ref, "/\\#");
        nameFromRef = nameParts[nameParts.length - 1];
    }
}