import React from 'react'
import { Select } from 'antd'
import { LocalizationContext } from '../util/LocalizationContext';

import '../../node_modules/flag-icon-css/css/flag-icon.min.css';

const { Option } = Select;

export default function LanguageSelectionDropdown(props) {

    const { locale, changeLanguage } = React.useContext(LocalizationContext);

    return (
        <Select defaultValue={locale} 
                onSelect={value => changeLanguage(value)} 
                style={{backgroundColor:"#001529"}} 
                dropdownStyle={{backgroundColor:"lightgray"}}
        >
            <Option value="hr">
                <span className="flag-icon flag-icon-hr" />
            </Option>
            <Option value="en">
                <span className="flag-icon flag-icon-gb" />
            </Option>
        </Select>

    )
}
