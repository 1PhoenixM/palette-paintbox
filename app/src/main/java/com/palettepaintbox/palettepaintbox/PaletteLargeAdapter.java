package com.palettepaintbox.palettepaintbox;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PaletteLargeAdapter extends RecyclerView.Adapter<PaletteLargeAdapter.PaletteHolder> {

    private ArrayList<Palette> mPalettes;

    public static class PaletteHolder extends RecyclerView.ViewHolder implements OnClickListener {

        private Palette mPalette;
        private LinearLayout mLinearLayout;
        private TextView mName;

        public PaletteHolder(View v) {
            super(v);
            mLinearLayout = v.findViewById(R.id.palette_row_linear_layout);
            //mName = v.findViewById(R.id.palette_row_name_text_view);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("RecyclerView", "CLICK!");
        }

        public void bindPalette(Palette palette) {
            mPalette = palette;
            //mName.setText(palette.getName());
            for(String color : palette.getColors()){
                Drawable backgroundShape = ContextCompat.getDrawable(mLinearLayout.getContext(),R.drawable.color_rectangle);
                String hexColor = "#" + color;
                backgroundShape.mutate().setColorFilter(Color.parseColor(hexColor), PorterDuff.Mode.MULTIPLY);
                Button colorButton = new Button(mLinearLayout.getContext());
                colorButton.setBackground(backgroundShape);
                colorButton.setText(hexColor);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200,200);
                layoutParams.setMargins(100,100,100,100);
                colorButton.setLayoutParams(layoutParams);
                mLinearLayout.addView(colorButton);
            }
        }
    }



    public PaletteLargeAdapter(ArrayList<Palette> palettes) {
        mPalettes = palettes;
    }

    @Override
    public PaletteLargeAdapter.PaletteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.palette_row, parent, false);
        return new PaletteHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(PaletteLargeAdapter.PaletteHolder holder, int position) {
        Palette itemPalette = mPalettes.get(position);
        holder.bindPalette(itemPalette);
    }

    @Override
    public int getItemCount() {
        return mPalettes.size();
    }
}