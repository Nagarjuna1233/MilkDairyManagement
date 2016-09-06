package com.milkdairy.services;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * A JTextField that accepts only integers.
 *
 * @author David Buzatto
 */
public class FloatField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FloatField() {
		super();
	}

	public FloatField(int cols) {
		super(cols);
	}

	@Override
	protected Document createDefaultModel() {
		return new UpperCaseDocument();
	}

	@SuppressWarnings("serial")
	static class UpperCaseDocument extends PlainDocument {

		@Override
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

			if (str == null) {
				return;
			}
			char[] chars = str.toCharArray();
			boolean ok = true;

			for (int i = 0; i < chars.length; i++) {

				if (chars[i] == '.') {
					if (this.getText(0, this.getLength()).contains(".")) {
						ok = false;
						break;
					}

					continue;

				}
				try {
					Integer.parseInt(String.valueOf(chars[i]));
				} catch (NumberFormatException exc) {
					ok = false;
					break;
				}

			}

			if (ok)
				super.insertString(offs, new String(chars), a);

		}
	}

}