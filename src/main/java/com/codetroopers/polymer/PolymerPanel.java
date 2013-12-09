package com.codetroopers.polymer;

import org.apache.wicket.Component;
import org.apache.wicket.IResourceListener;
import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.IMarkupFragment;
import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * @author cgatay
 */
public class PolymerPanel extends Panel implements IResourceListener {
    private final String webcomponentname;
    private boolean resourceRender = false;

    public PolymerPanel(final String id, final String webcomponentname) {
        super(id);
        this.webcomponentname = webcomponentname;
        add(new PolymerBehavior());
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);
    }

    @Override
    public IMarkupFragment getMarkup(final Component child) {
        if (resourceRender) {
            return super.getMarkup(child);
        } else {
            return Markup.NO_MARKUP;
        }
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        if (resourceRender) {
            super.onComponentTag(tag);
        } else {
            tag.setName(webcomponentname);
        }
    }

    @Override
    public void onResourceRequested() {
        try {
            resourceRender = true;
            getRequestCycle().getResponse().write(ComponentRenderer.renderComponent(this));
        } finally {
            resourceRender = false;
        }
    }

    @Override
    protected IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        if (!resourceRender){
            return null;
        }
        return super.newMarkupSourcingStrategy();
    }
}
