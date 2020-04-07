package com.captain.wds.degsinpattern.cloneable;

import java.util.ArrayList;

public class WordDocument implements Cloneable {

    private String mText;
    private ArrayList<String> mImgs = new ArrayList<>();

    @Override protected Object clone() {
        try {
            WordDocument document = (WordDocument) super.clone();
            document.mImgs = this.mImgs;
            document.mText = this.mText;
            return document;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public WordDocument() {
        System.out.println("WordDocument的构造函数");
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public ArrayList<String> getmImgs() {
        return mImgs;
    }

    public void addImgs(String mImg) {
        this.mImgs.add(mImg);
    }

    @Override public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (String mImg : this.mImgs) {
            stringBuffer.append(mImg + "/");

        }
        return "WordDocument{" +
                "mText='" + mText + '\'' +
                ", mImgs=" + stringBuffer.toString() +
                '}';
    }

    public static void main(String[] args) {
        WordDocument document = new WordDocument();
        document.setmText("文本1");
        document.addImgs("图片1");
        document.addImgs("图片2");
        document.addImgs("图片3");
        document.addImgs("图片4");
        System.out.println(document.toString());
        WordDocument clone = (WordDocument) document.clone();

        System.out.println(clone.toString());

        clone.setmText("修改clone的文本");
        clone.addImgs("😄😄.png");
        System.out.println(clone.toString());
        System.out.println(document.toString());

        //1：clone对象的修改不会影响document对象
        //2：通过clone()函数拷贝的对象不会执行构造函数
    }
}
