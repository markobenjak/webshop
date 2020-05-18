import React from 'react'
import { Select } from 'antd'
import { LocalizationContext } from './LocalizationContext';
import Flag from 'react-world-flags';

const { Option } = Select;

export default function LanguageSelectionDropdown(props) {

    const locale = React.useContext(LocalizationContext);

    return(
        <Select defaultValue={locale}>
            <Option value="hr">
                <div>
                    <Flag code="hr" />
                </div>
            </Option>
            <Option value="en">
                <div>
                    <Flag code="gb" />
                </div>
            </Option>
        </Select>
    )
}
