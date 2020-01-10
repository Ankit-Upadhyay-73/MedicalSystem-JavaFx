package company;

public class ViewCompany_TableView
{
    String CompanyName;
    String CompanyHolderName;
    String CompanyContact;
    String CompanyBranch;

    public ViewCompany_TableView(String companyName,String companyHolderName,String companyContact,String companyBranch) {
        CompanyName = companyName;
        CompanyHolderName = companyHolderName;
        CompanyContact = companyContact;
        CompanyBranch = companyBranch;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getCompanyHolderName() {
        return CompanyHolderName;
    }

    public String getCompanyContact() {
        return CompanyContact;
    }

    public String getCompanyBranch() {
        return CompanyBranch;
    }
}
