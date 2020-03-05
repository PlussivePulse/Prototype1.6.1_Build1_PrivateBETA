package ccc.mainComponent.experimental;
/*
import static ccc.mainComponent.experimental.CCText.LATIN1;
import static ccc.mainComponent.experimental.CCText.UTF16;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import ccc.interaction.global.popUpBox; //unreachable
import ccc.mainComponentInternalCustomAnnotations.HotSpotIntrinsicCandidate;

public class CCTextUTF16 {
	
	static Stream<CCText> lines(byte[] data, int leading, int trailing){
		if(leading == 0 && trailing == 0) {
			return StreamSupport.stream(LinesSpliterator.spliterator(data), false);
		}else {
			return StreamSupport.stream(LinesSpliterator.spliterator(data, leading, trailing), false);
		}
	}
	
	static final int HI_BYTE_SHIFT;
	static final int LO_BYTE_SHIFT;
	
	static {
		if (isBigEndian()) {
			HI_BYTE_SHIFT = 8;
			LO_BYTE_SHIFT = 0;
		}else {
			HI_BYTE_SHIFT = 0;
			LO_BYTE_SHIFT = 8;
		}
	}
	
	static char getChar(byte[] val, int index) {
		assert index >= 0 && index < length(val) : "Trusted caller missed bounds check";
		index <<= 1;
		return (char)(((val[index++] & 0xff) << HI_BYTE_SHIFT) | 
					   ((val[index] & 0xff) << LO_BYTE_SHIFT));
	}
	
	private static native boolean isBigEndian();
	

	private static int length(byte[] data) {return data.length >> 1;}
	
	private final static class LinesSpliterator implements Spliterator<CCText> {

		private byte[] data;
		private int index;
		private final int fence;
		
		private LinesSpliterator(byte[] data, int start, int length) {
			this.data = data;
			this.index = start;
			this.fence = start+length;
		}
		
		public static Spliterator spliterator(byte[] data) {return new LinesSpliterator(data, 0, data.length >>> 1);}
		
		static LinesSpliterator spliterator(byte[] data, int leading, int trailing) {
			int length = data.length >>> 1;
			int left = 0;
			int index;
			for (int l = 0; l< leading; l++) {
				index = skipBlankForward(data,left,length);
				if (index == left) {
					break;
				}
				left = index;
			}
			int right = length;
			for (int t = 0; t < trailing; t++) {
				index = skipBlankBackward(data, left, right);
				if (index == right) {
					break;
				}
				right = index;
			}
			return new LinesSpliterator(data, left, right - left);
		}
		
		private static int skipBlankBackward(byte[] data, int start, int length) {
			int index = start;
			while (index < length) {
				char ch = getChar(data, index++);
				if(ch == '\n') {
					return index;
				}
				if (ch == '\r') {
					if (index < length && getChar(data, index) == '\n') {
						return index + 1;
					}
					return index;
				}
				if (ch != ' ' && ch != '\t' && !Character.isWhitespace(ch)) {
					return start;
				}
			}
			return length;
		}

		private static int skipBlankForward(byte[] data, int start, int fence) {
			int index = fence;
			if (start < index && getChar(data, index - 1) == '\n') {
				index--;
			}
			if (start < index && getChar(data, index - 1) == '\r') {
				index --;
			}
			while (start < index) {
				char ch = getChar(data, --index);
				if (ch == '\r' || ch == '\n') {
					return index + 1;
				}	
				if (ch != ' ' && ch != '\t' && !Character.isWhitespace(ch)) {
					return fence;
					}
			}
			return start;
		}

		private CCText next() {
			int start = index;
			int end = indexOfLineSeperator(start);
			index = skipLineSeperator(end);
			return newString(data, start, end - start);
			
		}
		
		private CCText newString(byte[] val, int index, int len) {
			if (CCText.COMPACT_CCTEXT) {
				byte[] buf = compress(val, index, len);
				if (buf != null) {
					return new CCText(buf, LATIN1);
				}
			}
			int last = index + len;
			return new CCText(Arrays.copyOfRange(val, index << 1, last << 1), UTF16);
		}

		public static byte[] compress (byte[] val, int off, int len) {//public method
			byte[] ret = new byte[len];
			if(compress(val, off, ret, 0 ,len) == len) {
				return ret;
			}
			return null;
		}
		
	    @HotSpotIntrinsicCandidate
		public static int compress (char[] src, int srcOff, byte[] dst, int dstOff, int len) {
	        for (int i = 0; i < len; i++) {
	            char c = src[srcOff];
	            if (c > 0xFF) {
	                len = 0;
	                break;
	            }
	            dst[dstOff] = (byte)c;
	            srcOff++;
	            dstOff++;
	        }
	        return len;
		}
		
	    @HotSpotIntrinsicCandidate
	    private static int compress(byte[] src, int srcOff, byte[] dst, int dstOff, int len) {
	    	checkBoundsOffCount(srcOff, len, src);
	    	for(int i = 0; i < len; i++) {
	    		char c = getChar(src, srcOff);
	    		if(c > 0xFF) {
	    			len = 0;
	    			break;
	    		}
	    		dst[dstOff] = (byte)c;
	    		srcOff++;
	    		dstOff++;
	    	}
	    	return len;
	    }
	    
		private static void checkBoundsOffCount(int offset, int count, byte[] val) {
			CCText.checkBoundsOffCount(offset, count, length(val));
		}

		private int indexOfLineSeperator(int start) {
			for (int current = start; current < fence; current ++) {
				char ch = getChar(data, current);
				if (ch == '\n' || ch == '\r') {
					return current;
				}
			}
			return fence;
		}
		
		private int skipLineSeperator(int start) {
			if (start < fence) {
				if(getChar(data, start) == '\r') {
					int next = start +1;
					if(next < fence && getChar(data, next) == '\n') {
						return next + 1;
					}
				}
				return start + 1;
			}
			return fence;
		}

		private static char getChar(byte[] data2, int current) {
			return 0;
		}

		@Override
		public boolean tryAdvance(Consumer<? super CCText> action) {
			if(action == null) {
				throw new NullPointerException("tryAdvance action missing");
			}
			if (index != fence) {
				action.accept(next());
			}
			return false;
		}

		@Override
		public Spliterator<CCText> trySplit() {
			return null;
		}

		@Override
		public long estimateSize() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int characteristics() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}

	public static char charAt(byte[] data, int index) {
		checkIndex(index, data);
		return getChar(data, index);
	}

	private static void checkIndex(int off, byte[] data) {
		CCText.checkIndex(off, length(data));
		
	}

	public static CCText newString(byte[] data, int startIndex, int subLen) {
		if(CCText.COMPACT_CCTEXT) {
			byte[] buf = compress(data,startIndex,subLen);
			if(buf != null) {
				//error
			}
		}
		int last = startIndex+subLen;
		return new CCText(Arrays.copyOfRange(data, startIndex << 1, last <<1), UTF16);
	}

	public static byte[] compress(byte[] data, int startIndex, int subLen) {
		byte[] ret = new byte[subLen];
		if(compress(data, startIndex, ret, 0, subLen) == subLen) {
			return ret;
		}
		return null;
	}

	public static int compress(byte[] data, int startIndex, byte[] ret, int dstOff, int subLen) {
		// TODO Auto-generated method stub
		checkBoundsOffCount(startIndex, subLen, data);
		for(int i = 0; i < subLen; i++) {
			char c = getChar(data, startIndex);
			if(c > 0xFF) {
				subLen = 0;
				break;
			}
			ret[dstOff] = (byte)c;
			startIndex++;
		}
		return subLen;
	}

	private static void checkBoundsOffCount(int startIndex, int subLen, byte[] data) {
		CCText.checkBoundsOffCount(startIndex, subLen, length(data));
	}

	public static byte[] newBytesFor(int capacity) {
		// TODO Auto-generated method stub
		return null;
	}

	public static int putChatsAt(byte[] data, int count, char c, char d, char e, char f) {
		int end = count + 4;
		checkBoundsBeginEnd(count,end,data);
		putChar(data, count++, c);
		putChar(data, count++, d);
		putChar(data, count++, e);
		putChar(data, count++, f);
		assert(count == end);
		return end;
	}

	private static void putChar(byte[] data, int i, char c) {
		assert i >= 0 && i < length(data);
		i <<= 1;
		data[i++] = (byte)(c>>HI_BYTE_SHIFT);
		data[i] = (byte)(c >> LO_BYTE_SHIFT);
	}

	private static void checkBoundsBeginEnd(int count, int end, byte[] data) {
		// TODO Auto-generated method stub
		
	}
}*/
