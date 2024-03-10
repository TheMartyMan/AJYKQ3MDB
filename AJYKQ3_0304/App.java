import java.util.Enumeration;
import java.util.Iterator;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

public class App {

    public static void main(String[] args) throws Exception {
        test_connection();
        name_query();
    }

    public static void name_query() {

        int ldapPort = LDAPConnection.DEFAULT_PORT;
        int ldapVersion = LDAPConnection.LDAP_V3;
        String ldapHost = "127.0.0.1";
        String loginDN2 = "ou=csop_HU,ou=ev2020,ou=meinfo,dc=maxcrc,dc=com";
        String password2 = "H578";
        String searchBase = "cn=AJYKQ3,ou=ev2024,ou=meinfo,dc=maxcrc,dc=com";
        String searchFilter = "";
        int searchScope = LDAPConnection.SCOPE_BASE;
        LDAPConnection lc = new LDAPConnection();
        
        try {
            lc.connect(ldapHost, ldapPort);
            System.out.println("S1");
            lc.bind(ldapVersion, loginDN2, password2.getBytes());
            System.out.println("S2");
            LDAPSearchResults searchResults = lc.search(searchBase, searchScope, searchFilter, null, false);
            while (searchResults.hasMore()) {
                LDAPEntry nextEntry = null;
                try {
                    nextEntry = searchResults.next();
                    System.out.println("\n" + nextEntry.getDN());
                    com.novell.ldap.LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
                    Iterator allAttributes = attributeSet.iterator();
                    while (allAttributes.hasNext()) {
                        LDAPAttribute attribute = (LDAPAttribute) allAttributes.next();
                        String attributeName = attribute.getName();
                        if (attributeName.compareTo("sn") == 0) {
                            Enumeration<String> allValues = attribute.getStringValues();
                            String attributevalue = (String) allValues.nextElement();
                            System.out.println(attributeName + ": " +
                                    attributevalue);
                        }
                    }
                } catch (LDAPException e) {
                    System.out.println("HIBA: " + e.toString());
                }
            }
            lc.disconnect();
        } catch (Exception ee) {
        }

    }

    public static void test_connection() {

        int ldapPort = LDAPConnection.DEFAULT_PORT;
        int ldapVersion = LDAPConnection.LDAP_V3;
        String ldapHost = "127.0.0.1";
        String loginDN2 = "ou=csop_HU,ou=ev2020,ou=meinfo,dc=maxcrc,dc=com";
        String password2 = "H578";
        LDAPConnection lc = new LDAPConnection();
        boolean correct = true;
        try {
            lc.connect(ldapHost, ldapPort);
            System.out.println("S1");
            lc.bind(ldapVersion, loginDN2, password2.getBytes());
            System.out.println("S1");
            lc.disconnect();
        } catch (Exception ee) {
            correct = false;
        }
        System.out.println(correct ? "jelszó helyes." : "A megadott jelszó helytelen.\n");

    }

}
