package org.mybatis.jpetstore.web.actions;

import net.sourceforge.stripes.action.SessionScope;

@SessionScope
public class ProductActionBean extends AbstractActionBean{
    private String workingItemId;

    public void setWorkingItemId(String workingItemId) {
        this.workingItemId = workingItemId;
    }
}
