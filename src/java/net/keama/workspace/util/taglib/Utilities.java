/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.keama.workspace.util.taglib;
import org.apache.commons.lang3.StringEscapeUtils;

public class Utilities {
    public static String escapeJS(String value) {
        return StringEscapeUtils.escapeEcmaScript(value);
    }
}
