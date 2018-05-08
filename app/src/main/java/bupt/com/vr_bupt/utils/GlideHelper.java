package bupt.com.vr_bupt.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/**
 * Created by zhaojie on 16/10/10.
 */

public class GlideHelper {

    public static void showAvatarWithUrl(@Nullable Context context, String url, @Nullable ImageView imageView) {

//        Glide.with(context)
//                .load(CodeConstants.URL_PIC_PREFIX + url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.default_head_icon)
//                .error(R.drawable.default_head_icon)
//                .transform(new GlideCircleTransform(context))
//                .into(imageView);


    }

    public static void showImageWithUrl(@Nullable Context context, String url, @Nullable ImageView imageView) {
//        Glide.with(context)
//                .load(CodeConstants.URL_PIC_PREFIX + url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.default_title)
//                .error(R.drawable.default_title)
//                .dontAnimate()
//                .into(imageView);

    }
    public static void showImageWithFullUrl(@Nullable Context context, String url, @Nullable ImageView imageView) {
//        Glide.with(context)
//                .load(url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.default_ad_big)
//                .error(R.drawable.default_ad_big)
//                .dontAnimate()
//                .into(imageView);

    }

    public static void showBannerWithUrl(int tag, @Nullable Context context, String url, @Nullable ImageView imageView) {
//        if (tag == 1) {
//            Glide.with(context)
//                    .load(CodeConstants.URL_PIC_PREFIX + url)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .placeholder(R.drawable.default_ad_big)
//                    .error(R.drawable.default_ad_big)
//                    .dontAnimate()
//                    .into(imageView);
//        } else {
//            Glide.with(context)
//                    .load(CodeConstants.URL_PIC_PREFIX + url)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .placeholder(R.drawable.default_ad_little)
//                    .error(R.drawable.default_ad_little)
//                    .dontAnimate()
//                    .into(imageView);
//        }
    }
}
