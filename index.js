/** @format */

import { NativeModules, Platform, StatusBar } from 'react-native';

const { RNColorManager } = NativeModules;
const isAndroid = Platform.OS === 'android';
const isIOS = Platform.OS === 'ios';
const hasModule = !!RNColorManager;
const hasStatusBar = !!StatusBar;

export const ColorManager = {
  setStatusBarColor(color, animated = false, duration = 300) {
    if (isAndroid && hasModule) {
      RNColorManager.setStatusBarColor(color, animated, duration);
    } else if (isIOS && hasStatusBar) {
      StatusBar.setBackgroundColor(color, animated);
    }
  },

  setNavigationBarColor(color, animated = false, duration = 300) {
    if (isAndroid && hasModule) {
      RNColorManager.setNavigationBarColor(color, animated, duration);
    }
  },

  setRecentColor(color) {
    if (isAndroid && hasModule) {
      RNColorManager.setRecentColor(color);
    }
  },

  getThemeMode() {
    if (isAndroid && hasModule) {
      const mode = RNColorManager.isNightMode();
      return mode === 'no' || mode === 'auto' ? 'light' : 'dark';
    }

    return 'light';
  },
};

export default ColorManager;
