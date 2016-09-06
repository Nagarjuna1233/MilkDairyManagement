package com.milkdairy.services;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * A JTextField that accepts only integers.
 *
 * @author nagarjuna
 */
public class AlphabatField extends JTextField {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlphabatField() {
        super();
    }

    public AlphabatField( int cols ) {
        super( cols );
    }

    @Override
    protected Document createDefaultModel() {
        return new UpperCaseDocument();
    }

    static class UpperCaseDocument extends PlainDocument {

        @Override
        public void insertString( int offs, String str, AttributeSet a )
                throws BadLocationException {

            if ( str == null ) {
                return;
            }
            char[] chars = str.toCharArray();
            boolean ok = true;

            for ( int i = 0; i < chars.length; i++ ) {

                try {
                    Integer.parseInt( String.valueOf( chars[i] ) );
                    ok = false;
                    break;
                } catch ( NumberFormatException exc ) {
                    
                }


            }

            if ( ok )
                super.insertString( offs, new String( chars ), a );

        }
    }

}