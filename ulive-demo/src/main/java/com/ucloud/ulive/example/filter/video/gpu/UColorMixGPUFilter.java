package com.ucloud.ulive.example.filter.video.gpu;

import android.opengl.GLES20;

import com.ucloud.ulive.filter.UBaseVideoGPUFilter;

public class UColorMixGPUFilter extends UBaseVideoGPUFilter {

    private static String fragmentshader = "" +
            "precision mediump float;\n" +
            "varying mediump vec2 vCamTextureCoord;\n" +
            "uniform sampler2D uCamTexture;\n" +
            "uniform vec4 mixcolor;" +
            "void main(){\n" +
            "    vec4  color = texture2D(uCamTexture, vCamTextureCoord);\n" +
            "    gl_FragColor = vec4(mix(color.rgb,mixcolor.rgb,mixcolor.a),1.0);\n" +
            "}";

    private int mixColorLoc;

    private float r, g, b, a;

    public UColorMixGPUFilter(float r, float g, float b, float a) {
        super(null, fragmentshader);
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    @Override
    public void onInit(int VWidth, int VHeight) {
        super.onInit(VWidth, VHeight);
        mixColorLoc = GLES20.glGetUniformLocation(glProgram, "mixcolor");
    }

    @Override
    public void onPreDraw() {
        super.onPreDraw();
        GLES20.glUniform4f(mixColorLoc, r, g, b, a);
    }

    public void setMixColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}
