package ccc.mainComponent.experimental;

import ccc.mainComponentInternalCustomAnnotations.Native;
/*
import static ccc.mainComponent.experimental.CCText.COMPACT_CCTEXT;
import static ccc.mainComponent.experimental.CCText.LATIN1;
import static ccc.mainComponent.experimental.CCText.UTF16;

import java.util.Arrays;

abstract class AbstractCCTextBuilder implements Appendable, CharSequence {

	private static final byte[] EMPTYVALUE = new byte[0];

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;;
	
	byte[] data;
	byte coder;
	int count;
	
	AbstractCCTextBuilder() {
		data = EMPTYVALUE;
	}
	
	AbstractCCTextBuilder(int capacity){
		if (COMPACT_CCTEXT) {
			//error
		}else {
			data = CCTextUTF16.newBytesFor(capacity);
			coder = UTF16;
		}
	}
	
	public AbstractCCTextBuilder append(CCText text) {
		if (text == null) {
			return appendNull();
		}
		int len = text.length();
		ensureCapacityInternal(count + len);
		putStringAt(count, text);
		count += len;
		return this;
		
	}
	
	public AbstractCCTextBuilder append(String text) {
		return null;
		
	}
	
	private void putStringAt(int count2, CCText text) {
		// TODO Auto-generated method stub
		
	}

	private void ensureCapacityInternal(int i) {
		int oldCapacity = data.length >> coder;
		if(i-oldCapacity > 0) {
			data = Arrays.copyOf(data, newCapacity(i) << coder);
		}
		// TODO Auto-generated method stub
		//i = minimum capacity
		
	}

	private int newCapacity(int i) {
		// TODO Auto-generated method stub
		int oldCapacity = data.length >> coder;
			int newCapacity = (oldCapacity << 1) + 2;
			if(newCapacity - i < 0) {
				newCapacity = i;
			}
		int SAFE_BOUND = MAX_ARRAY_SIZE >> coder;
		return (newCapacity <= 0 || SAFE_BOUND - newCapacity < 0) ? hugeCapacity(i) : newCapacity;
	}

	private int hugeCapacity(int i) {
		int SAFE_BOUND = MAX_ARRAY_SIZE >> coder;
		int UNSAFE_BOUND = Integer.MAX_VALUE >> coder;
			if (UNSAFE_BOUND - i < 0 ) {
				throw new OutOfMemoryError();
			}
		return (i > SAFE_BOUND) ? i : SAFE_BOUND;
	}

	private AbstractCCTextBuilder appendNull() {
		// TODO Auto-generated method stub
		int count = this.count;
		byte[] data = this.data;
		if(isLatin1()) {
			
		}
		count = CCTextUTF16.putChatsAt(data, count, 'n', 'u', 'l', 'l');
		this.count = count;
		return null;
	}

	final byte getCode() {
		return COMPACT_CCTEXT ? coder : UTF16;
	}
	
	final boolean isLatin1() {
		// TODO Auto-generated method stub
		return COMPACT_CCTEXT && coder == LATIN1;
	}

	public abstract CCText toCCText();
	
	public CCText getPart(int start,int end) {
		return CCTextUTF16.newString(data, start, end-start);
	}
	
}*/
