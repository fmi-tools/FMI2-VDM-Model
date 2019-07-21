package fmi2vdm.elements;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;

abstract public class Element
{
	protected Integer lineNumber;
	
	protected Element(Locator locator)
	{
		lineNumber = locator.getLineNumber();
	}
	
	protected String stringOf(Attributes attributes, String name)
	{
		String value = attributes.getValue(name);
		
		if (value == null)
		{
			return null;
		}
		else
		{
			return value;
		}
	}
	
	protected Integer intOf(Attributes attributes, String name)
	{
		String value = attributes.getValue(name);
		
		if (value == null)
		{
			return null;
		}
		else
		{
			return Integer.parseInt(value);
		}
	}
	
	protected Double doubleOf(Attributes attributes, String name)
	{
		String value = attributes.getValue(name);
		
		if (value == null)
		{
			return null;
		}
		else
		{
			return Double.parseDouble(value);
		}
	}
	
	protected Boolean boolOf(Attributes attributes, String name)
	{
		String value = attributes.getValue(name);
		
		if (value == null)
		{
			return null;
		}
		else
		{
			return Boolean.parseBoolean(value);
		}
	}
	
	abstract void toVDM(String indent);
	
	public void add(Element element)
	{
		System.err.println("Cannot add " + element.getClass().getSimpleName() + " to " + getClass().getSimpleName());
	}
	
	protected void printOptional(String indent, Element element, String tail)
	{
		if (element == null)
		{
			System.out.print(indent + "nil" + tail);
		}
		else
		{
			element.toVDM(indent);
			System.out.print(tail);
		}
	}

	protected void printRawAttribute(String indent, Object attr, String tail)
	{
		if (attr != null && !attr.toString().isEmpty())
		{
			System.out.print(indent + attr + tail);
		} 
		else
		{
			System.out.print(indent + "nil" + tail);
		}
	}

	protected void printStringAttribute(String indent, String attr, String tail)
	{
		if (attr != null && ! attr.isEmpty())
		{
			System.out.print(indent + "\"" + attr + "\"" + tail);
		} 
		else
		{
			System.out.print(indent + "nil" + tail);
		}
	}

	protected void printQuoteAttribute(String indent, String attr, String tail)
	{
		if (attr != null && !attr.isEmpty())
		{
			System.out.print(indent + "<" + attr + ">" + tail);
		}
		else
		{
			System.out.print(indent + "nil" + tail);
		}
	}
	
	protected void printSequence(String indent, List<? extends Element> items, String tail)
	{
		printSeqSet(indent, items, tail, "[", "]");
	}
	
	protected void printSet(String indent, List<? extends Element> items, String tail)
	{
		printSeqSet(indent, items, tail, "{", "}");
	}
	
	private void printSeqSet(String indent, List<? extends Element> items, String tail, String open, String close)
	{
		if (items == null || items.isEmpty())
		{
			System.out.print(indent + "nil" + tail);
		}
		else
		{
			System.out.println(indent + open);
			String sep = "";
			
			for (Element item: items)
			{
				System.out.print(sep);
				item.toVDM(indent + "\t");
				sep = ",\n";
			}
			
			System.out.print("\n" + indent + close + tail);
		}
	}
}