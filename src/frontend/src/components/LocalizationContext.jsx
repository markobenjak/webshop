import React from 'react';

export const Localization = {
    languages: ["hr","en"],
    currentLanguage: "hr"
}

export const LocalizationContext = React.createContext(Localization);

