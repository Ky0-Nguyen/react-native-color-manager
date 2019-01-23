/**
 * @format
 * @flow
 */

import { NativeModules, Platform } from 'react-native';

const { RNColorManager } = NativeModules;

export const ColorManager = {
  setStatusBarColor(
    color: string,
    animated?: boolean = false,
    duration?: number = 300
  ): void {
    if (Platform.OS === 'android' && RNColorManager) {
      RNColorManager.setStatusBarColor(color, animated, duration);
    }
  },
  setNavigationBarColor(
    color: string,
    animated?: boolean = false,
    duration?: number = 300
  ): void {
    if (Platform.OS === 'android' && RNColorManager) {
      RNColorManager.setNavigationBarColor(color, animated, duration);
    }
  },
  setRecentColor(color: string): void {
    if (Platform.OS === 'android' && RNColorManager) {
      RNColorManager.setRecentColor(color);
    }
  }
};

export default ColorManager;
