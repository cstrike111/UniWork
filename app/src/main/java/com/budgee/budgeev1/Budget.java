package com.budgee.budgeev1;

import java.util.Date;

/**
 * Created by Will on 16/02/2017.
 */

public class Budget {
    private int budgetID;
    private Date budgetStartDate;
    private Date budgetFinishDate;

    public int getBudgetID() {
        return budgetID;
    }

    public Date getBudgetStartDate() {
        return budgetStartDate;
    }

    public Date getBudgetFinishDate() {
        return budgetFinishDate;
    }

    public void setBudgetID(int budgetID) {
        this.budgetID = budgetID;
    }

    public void setBudgetStartDate(Date budgetStartDate) {
        this.budgetStartDate = budgetStartDate;
    }

    public void setBudgetFinishDate(Date budgetFinishDate) {
        this.budgetFinishDate = budgetFinishDate;
    }
}
