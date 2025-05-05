'use client'
import { Input } from "@/components/ui/input"
import { useState } from "react"
import TextSearch from "./TextSearch"

const SearchBox = () => {
  const [textSearchInput, setTextSearchInput]= useState("")
  return(
    <>
      <TextSearch
        inputValue={textSearchInput}
        setInputValue={setTextSearchInput}
      />
    </>
  )
}

export default SearchBox