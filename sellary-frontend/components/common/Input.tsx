import React from "react";

interface InputBaseProps {
  label?: string;
  isRequired?: boolean;
}
interface InputSelectProps {
  options : TOption[];
  selectedValue: string;
  onSelectChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
}
interface InputRangeProps {
  setMinValue : (min : number)=>void;
  setMaxValue : (max : number)=>void;
}
type TOption={
  id : string;
  value : string;
}
const FlexBox =({children}:{children:React.ReactNode})=>{
  return(
    <div className="flex w-full">
      {children}
    </div>
  )
}
const Input = {
  Text: React.forwardRef<
    HTMLInputElement, 
    InputBaseProps & React.InputHTMLAttributes<HTMLInputElement>
  >(({ label, isRequired, className, ...props }, ref) => {
    return (
      <FlexBox>
        {label && (
          <label>
            {isRequired && <span>*</span>}
            {label}
          </label>
        )}
        <input
        className={className}
          ref={ref}
          {...props}
        />
      </FlexBox>
    );
  }),
  Select: React.forwardRef<
    HTMLInputElement, 
    InputBaseProps & InputSelectProps& React.InputHTMLAttributes<HTMLInputElement>
  >(({ label, isRequired, className,options,selectedValue,onSelectChange,...props }, ref) => {
    const handleSelectChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
      onSelectChange(e);
    };
    return (
      <FlexBox>
        <select
          className={className}
          onChange={(e)=>handleSelectChange(e)}
          value={selectedValue}>
          {options.map((option) => (
            <option key={option.id} value={option.value}>
              {option.value}
            </option>
          ))}
        </select>
        <input
          ref={ref}
          className="hidden"
          value = {selectedValue}
          readOnly
        />
      </FlexBox>
    );
  }),
  Range: React.forwardRef<
    HTMLInputElement, 
    InputBaseProps & InputRangeProps& React.InputHTMLAttributes<HTMLInputElement>
  >(({ label, isRequired, className,setMinValue, setMaxValue,...props }, ref) => {
    const handleMinOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
      setMinValue(Number(e.currentTarget.value));
    };
    const handleMaxOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
      setMaxValue(Number(e.currentTarget.value));
    };
    return (
      <FlexBox>
        {label && (
          <label>
            {isRequired && <span>*</span>}
            {label}
          </label>
        )}
        
        <input
          className={className}
          ref={ref}
          placeholder="min"
          onChange={(e)=>handleMinOnChange(e)}
          {...props}

        />
        <input
          className={className}
          ref={ref}
          placeholder="max"
          onChange={(e)=>handleMaxOnChange(e)}
          {...props}
        />
      </FlexBox>
    );
  }),
}
export default Input;