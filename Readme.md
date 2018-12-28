0. Android application
  - Create a gui with two buttons (open and play/pause) and text field.
  - Implement handling of two buttons:  
    By pushing open button get link on mp3 file or internet-radio from text field and start play. (Use android.media.MediaPlayer).  
    By pressing the pause / play button, stop playing or start playing music or radio.

1. Configure kernel for the emulator.  
   These steps required because default emulator image doesn't support kernel modules loading.  
  - References:  
     https://source.android.com/setup/build/building-kernels
  - Get sources  
     *git clone https://android.googlesource.com/kernel/goldfish*  
     *git checkout origin/android-goldfish-3.18*  
     *make x86_64_ranchu_defconfig*
  - Enable kernel modules loading  
     *make menuconfig*  
     You should select modules loading in the menu.  
  - Build kernel  
     *make -j4*
2. Build emulator image.  
    You need to setup environment as described in these links, step by step:  
  - References:  
    https://source.android.com/setup/build/initializing  
    https://source.android.com/setup/build/downloading  
    Possible issues may occur if skipped some steps. "repo init" should be called with args from next step.
  - Get sources  
    *repo init -u https://android.googlesource.com/platform/manifest -b android-8.1.0_r53*  
    *repo sync -c -j4*
    
    *. build/envsetup.sh*
    *lunch aosp_x86_64-eng*

    *make -j4*

3. Run emulator  
  - For event generation in the host side used simple application,
    which will create pseudo-terminal interface pair and send single char to it
    on the button press. Sources and binary are located here:
    https://github.com/hrandib/AppButton

    *emulator -kernel /path/to/kernel/arch/x86/boot/bzImage
    -show-kernel -writable-system -qemu -serial /path/to/pseudo_terminal_slave*

4. Build kernel module template
    - References:
    https://drive.google.com/drive/folders/1NbM2NH3c0cGmpvcHnj7gh_FoS4zKyUPN

    In the makefile we need to use the path to the kernel from 1st point.

    *adb root && adb remount*  
    *adb push module.ko /vendor/lib/modules/*  
    *adb shell insmod /vendor/lib/modules/module.ko*  

5. Implement serial event capture and futher uevent generation in the kernel module.
  
6. Implement HAL layer
  
7. Bind uevent on pause/play button of the application from point 0.
