package com.codetroopers.polymer;

import org.apache.wicket.Component;
import org.apache.wicket.IResourceListener;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.StringHeaderItem;

/**
 * @author cgatay
 */
public class PolymerBehavior extends Behavior {

    @Override
    public void renderHead(final Component component, final IHeaderResponse response) {
        super.renderHead(component, response);
        response.render(JavaScriptHeaderItem.forUrl("//cdnjs.cloudflare.com/ajax/libs/polymer/0.0.20131107/polymer.min.js", "polymerjs"));
        response.render(StringHeaderItem.forString("<link rel=\"import\" href=\""+component.urlFor(IResourceListener.INTERFACE, null)+"\">"));

    }
}
