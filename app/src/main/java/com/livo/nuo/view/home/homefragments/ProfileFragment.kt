package com.livo.nuo.view.home.homefragments


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.livo.nuo.R
import com.livo.nuo.databinding.BottomSheetlanguagecodeBinding
import com.livo.nuo.manager.SessionManager
import com.livo.nuo.utility.AndroidUtil
import com.livo.nuo.view.Splash_Screen
import com.livo.nuo.view.profile.*
import com.livo.nuo.viewModel.ViewModelFactory
import com.livo.nuo.viewModel.profile.ProfileViewModel

import java.util.*


class ProfileFragment : Fragment() {

    private var currActivity : Activity? = null
    lateinit var rlProfile : RelativeLayout
    lateinit var tvSubmit : TextView
    lateinit var tvApplicationSettings : TextView

    lateinit var tvFAQ : TextView
    lateinit var tvHelpnSupport : TextView
    lateinit var rlRefer : RelativeLayout
    lateinit var rlLogout : RelativeLayout
    lateinit var rllanguage : RelativeLayout

    lateinit var imgUser : ImageView
    lateinit var tvShimmerImage: ShimmerFrameLayout
    lateinit var tvUserName : TextView
    lateinit var tvUserAge : TextView



    var bottomsheetlanguagecode : BottomSheetDialog? = null

    private var profileViewModel : ProfileViewModel? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root= inflater.inflate(R.layout.fragment_profile, container, false)

        tvSubmit = root.findViewById(R.id.tvSubmit)
        rlProfile = root.findViewById(R.id.rlProfile)
        tvApplicationSettings = root.findViewById(R.id.tvApplicationSettings)
        tvFAQ = root.findViewById(R.id.tvFAQ)
        tvHelpnSupport = root.findViewById(R.id.tvHelpnSupport)
        rlRefer  = root.findViewById(R.id.rlRefer)
        rlLogout = root.findViewById(R.id.rlLogout)
        rllanguage  =root.findViewById(R.id.rllanguage)

        imgUser = root.findViewById(R.id.imgUser)
        tvShimmerImage=root.findViewById(R.id.tvShimmerImage)

        tvUserName = root.findViewById(R.id.tvUserName)
        tvUserAge = root.findViewById(R.id.tvUserAge)


        rlProfile.setOnClickListener({

            val profile = Intent(requireActivity() , SenderProfileActivity :: class.java)
            startActivity(profile)
        })
        tvSubmit.setOnClickListener({
            val intent = Intent(requireActivity(), TransporterApplicationActivity::class.java)
            startActivity(intent)
        })
        tvApplicationSettings.setOnClickListener({

            val AppSetting = Intent(requireActivity() , ApplicationSetting :: class.java)
            startActivity(AppSetting)
        })

        tvFAQ.setOnClickListener({

            val Faq = Intent(requireActivity() , FAQActivity :: class.java)
            startActivity(Faq)

        })

        tvHelpnSupport.setOnClickListener({

            val HelpnSupport = Intent(requireActivity() , HelpAndSupportActivity :: class.java)
            startActivity(HelpnSupport)

        })

        rlRefer.setOnClickListener({

            val Refer = Intent(requireActivity() , ReferralActivity :: class.java)
            startActivity(Refer)
        })

        rlLogout.setOnClickListener({

            SessionManager.clear()
            val logout = Intent(requireActivity() , Splash_Screen :: class.java)
            startActivity(logout)
        })

        rllanguage.setOnClickListener({

            openBottomPopup()

        })

        initViews()

        return root
    }

    fun initViews(){

        currActivity = requireActivity()

        currActivity?.application?.let {
            profileViewModel = ViewModelProvider(
                ViewModelStore(),
                ViewModelFactory(it)
            ).get(ProfileViewModel::class.java)
        }

        profileViewModel?.let {
            if (currActivity.let { ctx -> AndroidUtil.isInternetAvailable(ctx!!) } == true) {
                it.getUserSetting()
            }
        }

        observers()

    }

    fun observers(){

        profileViewModel?.getMutableLiveDataUserSetting()?.observe(currActivity as LifecycleOwner, androidx.lifecycle.Observer {

            Glide.with(requireActivity()).addDefaultRequestListener(object : RequestListener<Any> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Any>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Any?,
                    model: Any?,
                    target: Target<Any>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
                .load(it.data.profile_image).placeholder(requireActivity().getDrawable(R.drawable.grey_round_shape))
                .error(requireActivity().getDrawable(R.drawable.grey_round_shape)).into(imgUser)


            tvUserName.text = it.data.first_name+" "+it.data.last_name
            tvUserAge.text = it.data.age +" "+"years old"


        })

    }


    private fun openBottomPopup(){

        bottomsheetlanguagecode = BottomSheetDialog(requireActivity())
            var bottomSheetLanguageDialogBinding =
                DataBindingUtil.inflate<BottomSheetlanguagecodeBinding>(
                    LayoutInflater.from(requireActivity()),
                    R.layout.bottom_sheetlanguagecode,null,false
                )

        bottomsheetlanguagecode?.setContentView(bottomSheetLanguageDialogBinding!!.root)
            Objects.requireNonNull<Window>(bottomsheetlanguagecode?.window)
                .setBackgroundDrawableResource(android.R.color.transparent)


        var imgDanishLang = bottomsheetlanguagecode!!.findViewById<ImageView>(R.id.imgDanishLang)
        var imgEnglishLang = bottomsheetlanguagecode!!.findViewById<ImageView>(R.id.imgEnglishLang)
        var imgDenmarkLang = bottomsheetlanguagecode!!.findViewById<ImageView>(R.id.imgDenmarkLang)
        var llCancel = bottomsheetlanguagecode!!.findViewById<LinearLayout>(R.id.llCancel)
        var llConfirmChange = bottomsheetlanguagecode!!.findViewById<LinearLayout>(R.id.llConfirmChange)





        imgDanishLang?.setOnClickListener({

            val animation = AnimationUtils.loadAnimation(requireActivity(), R.anim.fade_in)
            imgDanishLang.startAnimation(animation)

            bottomSheetLanguageDialogBinding.tvDanishLang.setTextColor(requireActivity().resources.getColor(R.color.livo_heading_black))
            bottomSheetLanguageDialogBinding.tvDenmarkLang.setTextColor(requireActivity().resources.getColor(R.color.black_40_opacity))
            bottomSheetLanguageDialogBinding.tvEnglishLang.setTextColor(requireActivity().resources.getColor(R.color.black_40_opacity))
        })

        imgEnglishLang?.setOnClickListener({

            val animation = AnimationUtils.loadAnimation(requireActivity(), R.anim.language_in)
            imgEnglishLang.startAnimation(animation)

            bottomSheetLanguageDialogBinding.tvDanishLang.setTextColor(requireActivity().resources.getColor(R.color.black_40_opacity))
            bottomSheetLanguageDialogBinding.tvDenmarkLang.setTextColor(requireActivity().resources.getColor(R.color.black_40_opacity))
            bottomSheetLanguageDialogBinding.tvEnglishLang.setTextColor(requireActivity().resources.getColor(R.color.livo_heading_black))
        })
        imgDenmarkLang?.setOnClickListener({

            val animation = AnimationUtils.loadAnimation(requireActivity(), R.anim.language_in)
            imgDenmarkLang.startAnimation(animation)

            bottomSheetLanguageDialogBinding.tvDanishLang.setTextColor(requireActivity().resources.getColor(R.color.black_40_opacity))
            bottomSheetLanguageDialogBinding.tvDenmarkLang.setTextColor(requireActivity().resources.getColor(R.color.livo_heading_black))
            bottomSheetLanguageDialogBinding.tvEnglishLang.setTextColor(requireActivity().resources.getColor(R.color.black_40_opacity))
        })

        llConfirmChange?.setOnClickListener({

            bottomSheetLanguageDialogBinding.llCancel.setBackground(requireActivity()!!.resources.getDrawable(R.drawable.grey_round_shape_45_opacity))
            bottomSheetLanguageDialogBinding.llConfirmChange.setBackground(requireActivity().resources.getDrawable(R.drawable.blue_round_shape))

        })
        llCancel?.setOnClickListener({

            bottomSheetLanguageDialogBinding.llConfirmChange.setBackground(requireActivity().resources.getDrawable(R.drawable.blue_round_shape))
            bottomSheetLanguageDialogBinding.llCancel.setBackground(requireActivity().resources.getDrawable(R.drawable.grey_round_shape_45_opacity))

        })

        bottomsheetlanguagecode?.show()
    }


    companion object {
        fun newInstance() : Fragment {
            val f = ProfileFragment()
            return f
        }
    }
}