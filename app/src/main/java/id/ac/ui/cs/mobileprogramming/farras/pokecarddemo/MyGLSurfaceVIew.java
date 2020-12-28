package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLSurfaceVIew extends GLSurfaceView {
    private final MyGLRenderer mRenderer;

    public MyGLSurfaceVIew(Context context) {
        super(context);
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);
        mRenderer = new MyGLRenderer();
        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);

    }
}
